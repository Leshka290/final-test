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
import ru.liga.dto.ItemDto;
import ru.liga.dto.OrderDto;
import ru.liga.dto.RestaurantNameDto;
import ru.liga.service.KitchenService;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = KitchenController.class)
public class KitchenControllerTest {
    private MockMvc mvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private ObjectMapper mapper;
    @MockBean
    private KitchenService kitchenService;

    private OrderDto dto;
    private Long customerId;
    private final LocalDateTime created =
            LocalDateTime.of(2023, 11, 7, 8, 11, 11);

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
        assertThat(kitchenService).isNotNull();
    }

    @Test
    void testAcceptOrder() throws Exception {
        doNothing().when(kitchenService).acceptOrder(dto.getId());

        mvc.perform(post("/kitchen/accept/" + dto.getId())
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("orderId", String.valueOf(dto.getId()))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testDeclineOrder() throws Exception {
        doNothing().when(kitchenService).declineOrder(dto.getId());

        mvc.perform(post("/kitchen/decline/" + dto.getId())
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("orderId", String.valueOf(dto.getId()))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testReadyOrder() throws Exception {
        doNothing().when(kitchenService).readyOrder(dto.getId(), "key");

        mvc.perform(post("/kitchen/ready/" + dto.getId())
                        .param("routingKey", "key")
                        .param("orderId", String.valueOf(dto.getId()))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
