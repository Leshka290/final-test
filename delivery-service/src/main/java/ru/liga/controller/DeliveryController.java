package ru.liga.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.liga.dto.DeliveryDto;
import ru.liga.dto.OrderActionDto;
import ru.liga.service.DeliveryService;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/delivery")
@RestController
public class DeliveryController {
    private final DeliveryService deliveryService;

    @Operation(summary = "Получение списка доставок")
    @GetMapping("/all")
    public ResponseEntity<List<DeliveryDto>> getAllDelivery() {
        log.info("Request GET all delivery");
        return ResponseEntity.ok(deliveryService.getAllDeliveries());
    }

    @Operation(summary = "Изменение статуса заказа")
    @PostMapping()
    public void updateOrderStatus(@RequestBody OrderActionDto orderAction) {
        log.info("Request POST delivery order action {}", orderAction);

        deliveryService.updateOrderStatus(orderAction);
    }
}
