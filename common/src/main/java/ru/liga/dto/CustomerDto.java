package ru.liga.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "Customer")
public class CustomerDto {
    private String address;
    private Double distance;
}
