package ru.liga.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.liga.dto.DeliveryDto;
import ru.liga.dto.enums.DeliveryStatus;
import ru.liga.dto.enums.OrderAction;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/delivery")
@RestController
public class DeliveryController {

    @Operation(summary = "Получение списка доставок по статусу")
    @GetMapping("/{status}")
    public ResponseEntity<List<DeliveryDto>> getAllByStatus(@PathVariable DeliveryStatus status) {
        log.info("Request GET delivery by status {}", status);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Получение списка доставок")
    @GetMapping("/all")
    public ResponseEntity<List<DeliveryDto>> getAllDelivery() {
        log.info("Request GET all delivery");
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Изменение статуса заказа")
    @PostMapping("/{id}")
    public ResponseEntity<?> setOrderAction(@PathVariable Long id, OrderAction orderAction) {
        log.info("Request POST delivery order action {}", orderAction);
        return ResponseEntity.ok().build();
    }
}
