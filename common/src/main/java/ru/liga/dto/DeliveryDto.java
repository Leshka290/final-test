package ru.liga.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Schema(name = "Delivery")
public class DeliveryDto {
    private Long orderId;
    private RestaurantDto restaurant;
    private CustomerDto customer;
    private BigDecimal payment;

}
