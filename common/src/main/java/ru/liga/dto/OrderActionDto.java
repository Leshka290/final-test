package ru.liga.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.liga.dto.enums.OrderStatus;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@Schema(name = "OrderActions")
public class OrderActionDto {
    @NotNull
    private Long id;
    @NotNull
    private OrderStatus status;
}
