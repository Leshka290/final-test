package ru.liga.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.liga.dto.MenuItem;
import ru.liga.dto.OrderDto;
import ru.liga.dto.RestaurantNameDto;
import ru.liga.entity.OrderItem;
import ru.liga.entity.Order;
import ru.liga.entity.Restaurant;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

//    RestaurantNameDto map(Restaurant restaurant);
    OrderDto orderToOrderDto(Order order);

    @Mapping(source = "order", target = "order.status")
    List<OrderDto> ordersToOrderDto(List<Order> orders);

    @Mapping(source = "item.id", target = "idMenuItem")
    MenuItem itemsToMenuItems(int quantity, OrderItem item);
}
