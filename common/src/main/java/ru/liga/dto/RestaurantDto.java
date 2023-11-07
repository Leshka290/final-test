package ru.liga.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "Restaurants")
public class RestaurantDto {
    private String address;
    private Double distance;
}
