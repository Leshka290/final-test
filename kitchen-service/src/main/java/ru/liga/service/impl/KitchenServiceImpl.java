package ru.liga.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ru.liga.client.KitchenClient;
import ru.liga.dto.OrderActionDto;
import ru.liga.dto.enums.OrderStatus;
import ru.liga.service.KitchenService;

@Slf4j
@RequiredArgsConstructor
@Service
public class KitchenServiceImpl implements KitchenService {
    private final KitchenClient kitchenClient;
    private final RabbitTemplate rabbitTemplate;

    @Override
    public void acceptOrder(Long orderId) {
        OrderActionDto dto = new OrderActionDto(orderId, OrderStatus.KITCHEN_ACCEPTED);
        kitchenClient.updateOrderStatus(dto);
    }

    @Override
    public void declineOrder(Long orderId) {
        OrderActionDto dto = new OrderActionDto(orderId, OrderStatus.KITCHEN_DENIED);
        kitchenClient.updateOrderStatus(dto);
    }

    @Override
    public void readyOrder(Long orderId, String routingKey) {
        rabbitTemplate.convertAndSend("directExchange", routingKey, orderId);
    }
}
