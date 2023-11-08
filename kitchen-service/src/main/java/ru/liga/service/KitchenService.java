package ru.liga.service;

public interface KitchenService {
    void acceptOrder(Long orderId);

    void declineOrder(Long orderId);

    void readyOrder(Long orderId, String routingKey);
}
