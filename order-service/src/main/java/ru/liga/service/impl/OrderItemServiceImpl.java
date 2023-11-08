package ru.liga.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.liga.dto.CreatedOrderDto;
import ru.liga.dto.MenuItem;
import ru.liga.entity.Order;
import ru.liga.entity.OrderItem;
import ru.liga.entity.RestaurantMenuItem;
import ru.liga.repository.OrderItemRepository;
import ru.liga.repository.RestaurantMenuItemRepository;
import ru.liga.service.OrderItemService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final RestaurantMenuItemRepository restaurantMenuItemRepository;

    @Override
    public List<OrderItem> createOrderItems(Order order, CreatedOrderDto createOrderDto) {
        Map<Long, Integer> itemsQuantity = createOrderDto.getMenuItems().stream()
                .collect(Collectors.toMap(MenuItem::getIdMenuItem, MenuItem::getQuantity));

        List<Long> idsMenuItems = new ArrayList<>(itemsQuantity.keySet());
        List<RestaurantMenuItem> menuItems = restaurantMenuItemRepository.findAllByIdIn(idsMenuItems);
        List<OrderItem> orderItems = new ArrayList<>();

        for (RestaurantMenuItem menuItem : menuItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setRestaurantMenuItem(menuItem);
            orderItem.setPrice(menuItem.getPrice());
            orderItem.setQuantity(itemsQuantity.get(menuItem.getId()));
            orderItems.add(orderItem);
        }
        return orderItemRepository.saveAll(orderItems);
    }
}
