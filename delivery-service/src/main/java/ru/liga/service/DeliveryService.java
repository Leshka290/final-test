package ru.liga.service;

import ru.liga.dto.DeliveryDto;
import ru.liga.dto.OrderActionDto;

import java.util.List;

public interface DeliveryService {
    void updateOrderStatus(OrderActionDto orderAction);

    List<DeliveryDto> getAllDeliveries();
}
