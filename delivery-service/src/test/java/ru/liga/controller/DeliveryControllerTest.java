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
import ru.liga.dto.OrderActionDto;
import ru.liga.dto.enums.OrderStatus;
import ru.liga.service.DeliveryService;

import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = DeliveryController.class)
public class DeliveryControllerTest {
    private MockMvc mvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private ObjectMapper mapper;
    @MockBean
    private DeliveryService deliveryService;

    private OrderActionDto orderActionDto;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        orderActionDto = OrderActionDto.builder()
                .id(1L)
                .status(OrderStatus.CUSTOMER_CREATED)
                .build();
    }

    @Test
    void contextLoads() {
        assertThat(deliveryService).isNotNull();
    }

    @Test
    void testUpdateOrderStatus() throws Exception {
        doNothing().when(deliveryService).updateOrderStatus(orderActionDto);

        mvc.perform(post("/delivery")
                        .content(mapper.writeValueAsString(orderActionDto))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("orderActionDto", String.valueOf(orderActionDto.getStatus()))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
