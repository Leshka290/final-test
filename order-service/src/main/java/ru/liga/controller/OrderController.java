package ru.liga.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.liga.dto.CreatedOrderDto;
import ru.liga.dto.CreatedOrderToDeliveryDto;
import ru.liga.dto.OrderDto;
import ru.liga.dto.enums.OrderStatus;
import ru.liga.service.OrderService;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/order")
@RestController
public class OrderController {
    private final OrderService orderService;

    @Operation(summary = "Создание заказа")
    @ApiResponse(responseCode = "200", description = "Created",
            content = @Content(
                    schema = @Schema(implementation = CreatedOrderToDeliveryDto.class))
    )
    @PostMapping()
    public ResponseEntity<CreatedOrderToDeliveryDto> createOrder(@RequestBody CreatedOrderDto createOrderDto,
                                                                 Long customerId) {
        log.info("Request POST createOrder");
        return ResponseEntity.ok(orderService.createOrder(createOrderDto, customerId));
    }

    @Operation(summary = "Получение заказа по id")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @ApiResponse(responseCode = "404", description = "Not Found")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable Long id) {
        log.info("Request GET orderDto by id {}", id);
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @Operation(summary = "Обновление статуса заказа")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @PutMapping("/update/{id}")
    private void updateOrderStatusById(@PathVariable Long id, @RequestParam OrderStatus status) {
        log.info("Request PUT order status {} by id {}", status, id);

        orderService.updateOrderStatusById(id, status);
        ResponseEntity.ok().build();
    }

    @Operation(summary = "Получение всех заказов")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    @GetMapping("/all")
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        log.info("Request GET all orders");
        return ResponseEntity.ok(orderService.getAllOrders());
    }
}
