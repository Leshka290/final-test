package ru.liga.service;

import ru.liga.dto.CreatedOrderDto;
import ru.liga.dto.CreatedOrderToDeliveryDto;
import ru.liga.dto.OrderDto;
import ru.liga.dto.enums.OrderStatus;

import java.util.List;

public interface OrderService {

    CreatedOrderToDeliveryDto createOrder(CreatedOrderDto createOrderDto, Long customerId);

    OrderDto getOrderById(Long orderId);

    void updateOrderStatusById(Long orderId, OrderStatus status);

    List<OrderDto> getAllOrders();
}
