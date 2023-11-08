package ru.liga.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.liga.service.KitchenService;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/kitchen")
@RestController
public class KitchenController {
    private final KitchenService kitchenService;

    @Operation(summary = "Принять заказ")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @PostMapping("/accept/{orderId}")
    public ResponseEntity<?> acceptOrder(@PathVariable Long orderId) {
        log.info("Request POST to accept order by id {}", orderId);

        kitchenService.acceptOrder(orderId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Отклонить заказ")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @PostMapping("/decline/{orderId}")
    public ResponseEntity<?> declineOrder(@PathVariable Long orderId) {
        log.info("Request POST to decline order by id {}", orderId);

        kitchenService.declineOrder(orderId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Заказ готов")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @PostMapping("/ready/{orderId}")
    public ResponseEntity<?> readyOrder(@PathVariable Long orderId, @RequestParam(name = "routingKey") String routingKey) {
        log.info("Received POST request to ready order by id {} to the routingKey {}", orderId, routingKey);

        kitchenService.readyOrder(orderId, routingKey);
        return ResponseEntity.ok().build();
    }
}
