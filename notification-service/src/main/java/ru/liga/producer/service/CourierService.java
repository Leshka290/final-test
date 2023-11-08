package ru.liga.producer.service;

import ru.liga.dto.enums.OrderAction;

public interface CourierService {
    void sendOrderStatus(OrderAction orderAction);
}
