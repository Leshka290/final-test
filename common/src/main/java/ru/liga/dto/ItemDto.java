package ru.liga.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "items")
public class ItemDto {
    private int price;
    private int quantity;
    private String description;
    private String image;
}
