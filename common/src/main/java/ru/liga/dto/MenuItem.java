package ru.liga.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "MenuItems")
public class MenuItem {
    private int quantity;
    private int idMenuItem;
}
