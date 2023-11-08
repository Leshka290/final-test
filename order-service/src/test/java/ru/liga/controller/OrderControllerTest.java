package ru.liga.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.liga.dto.*;
import ru.liga.service.OrderService;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = OrderController.class)
public class OrderControllerTest {
    private MockMvc mvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private ObjectMapper mapper;
    @MockBean
    private OrderService orderService;

    private final LocalDateTime created =
            LocalDateTime.of(2023, 11, 7, 8, 11, 11);
    private final String timestamp = created.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    private OrderDto dto;
    private Long customerId;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        dto = OrderDto.builder()
                .id(1L)
                .timestamp(created)
                .restaurant(new RestaurantNameDto("Restaurant"))
                .items(List.of(new ItemDto()))
                .build();

        customerId = 1L;
    }

    @Test
    void contextLoads() {
        assertThat(orderService).isNotNull();
    }

    @Test
    void testCreateOrder() throws Exception {
        CreatedOrderToDeliveryDto deliverDto = new CreatedOrderToDeliveryDto();
        deliverDto.setId(dto.getId());
        deliverDto.setSecretPaymentUrl("111");
        deliverDto.setEstimatedTimeOfArrival(LocalDateTime.now());

        String formattedArrival = deliverDto.getEstimatedTimeOfArrival()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        CreatedOrderDto createdOrderDto = new CreatedOrderDto(1L, List.of(new MenuItem(1, 1L)));

        when(orderService.createOrder(any(CreatedOrderDto.class), anyLong()))
                .thenReturn(deliverDto);

        mvc.perform(post("/order")
                        .content(mapper.writeValueAsString(createdOrderDto))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("customerId", String.valueOf(customerId))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(dto.getId()), Long.class))
                .andExpect(jsonPath("$.estimatedTimeOfArrival", is(formattedArrival)));
    }

    @Test
    void testGetOrderById() throws Exception {
        when(orderService.getOrderById(dto.getId()))
                .thenReturn(dto);

        mvc.perform(get("/order/" + dto.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(dto.getId()), Long.class))
                .andExpect(jsonPath("$.restaurant.name", is(dto.getRestaurant().getName())))
                .andExpect(jsonPath("$.timestamp", is(timestamp)))
                .andExpect(jsonPath("$.items", hasSize(1)));
    }

    @Test
    void testGetAllOrders() throws Exception {
        when(orderService.getAllOrders())
                .thenReturn(List.of(dto));

        mvc.perform(get("/order/all")
                        .param("status", "CUSTOMER_CREATED")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(dto.getId()), Long.class))
                .andExpect(jsonPath("$[0].restaurant.name", is(dto.getRestaurant().getName())))
                .andExpect(jsonPath("$[0].timestamp", is(timestamp)))
                .andExpect(jsonPath("$[0].items", hasSize(1)));
    }
}
