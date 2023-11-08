package ru.liga.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "RestaurantMenuItem")
public class RestaurantMenuItemDto {
    private Long id;
    private String name;
    private Integer price;
    private String description;
    private RestaurantDto restaurant;
    private String image;
}
