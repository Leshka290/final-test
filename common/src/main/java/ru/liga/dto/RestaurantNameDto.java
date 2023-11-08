package ru.liga.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "RestaurantName")
public class RestaurantNameDto {
    private String name;
}
