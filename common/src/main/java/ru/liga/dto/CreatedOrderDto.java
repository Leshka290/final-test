package ru.liga.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Schema(name = "CreateOrders")
public class CreatedOrderDto {
    private Long restaurantId;
    private List<MenuItem> menuItems;
}
