package ru.liga.service;

import ru.liga.dto.CreatedOrderDto;
import ru.liga.dto.MenuItem;
import ru.liga.entity.Order;
import ru.liga.entity.OrderItem;

import java.util.List;

public interface OrderItemService {
    List<OrderItem> createOrderItems(Order order, CreatedOrderDto createOrderDto);
}
