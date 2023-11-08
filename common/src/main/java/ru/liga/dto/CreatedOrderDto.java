package ru.liga.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(name = "CreateOrders")
public class CreatedOrderDto {
    private Long restaurantId;
    private List<MenuItem> menuItems;
}
