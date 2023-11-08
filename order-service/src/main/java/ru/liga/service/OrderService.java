package ru.liga.service;

import ru.liga.dto.CreatedOrderDto;
import ru.liga.dto.CreatedOrderToDeliveryDto;

public interface OrderService {

    CreatedOrderToDeliveryDto createOrder(CreatedOrderDto createOrderDto);
}
