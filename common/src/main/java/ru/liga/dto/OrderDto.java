package ru.liga.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(name = "Orders")
public class OrderDto {
    private Long id;
    private RestaurantNameDto restaurant;
    private String timestamp;
    private List<ItemDto> items;
}