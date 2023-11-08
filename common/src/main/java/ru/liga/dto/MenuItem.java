package ru.liga.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(name = "MenuItems")
public class MenuItem {
    private int quantity;
    private Long idMenuItem;
}
