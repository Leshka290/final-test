package ru.liga.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.liga.dto.CustomerDto;
import ru.liga.dto.DeliveryDto;
import ru.liga.dto.OrderActionDto;
import ru.liga.dto.RestaurantDto;
import ru.liga.dto.enums.OrderStatus;
import ru.liga.entity.Courier;
import ru.liga.entity.Customer;
import ru.liga.entity.Order;
import ru.liga.entity.Restaurant;
import ru.liga.exception.CourierNotFoundException;
import ru.liga.exception.OrderNotFoundException;
import ru.liga.repository.CourierRepository;
import ru.liga.repository.OrderRepository;
import ru.liga.service.DeliveryService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class DeliveryServiceImpl implements DeliveryService {
    private final OrderRepository orderRepository;
    private final CourierRepository courierRepository;

    @Override
    public void updateOrderStatus(OrderActionDto orderAction) {
        log.info("Current method is - setOrderAction");
        Order order = orderRepository.findById(orderAction.getId()).orElseThrow(OrderNotFoundException::new);
        Courier courier = courierRepository.findAll().stream().findAny().orElseThrow(CourierNotFoundException::new);

        switch (orderAction.getStatus()) {
            case DELIVERY_PICKING:
                order.setStatus(OrderStatus.DELIVERY_PICKING);
                order.setCourier(courier);
                orderRepository.save(order);
                break;
            case DELIVERY_COMPLETE:
                order.setStatus(OrderStatus.DELIVERY_COMPLETE);
                orderRepository.save(order);
                break;
        }
    }

    @Override
    public List<DeliveryDto> getAllDeliveries() {
        log.info("Current method is - getAllDeliveries");

        List<Order> orders = orderRepository.findAll();

        Map<Long, Restaurant> restaurants = orders.stream()
                .collect(Collectors.toMap(Order::getId, Order::getRestaurant));
        Map<Long, Customer> customers = orders.stream()
                .collect(Collectors.toMap(Order::getId, Order::getCustomer));

        List<DeliveryDto> deliveries = new ArrayList<>();
        for (Order order : orders) {
            DeliveryDto deliveryDto = new DeliveryDto();
            deliveryDto.setOrderId(order.getId());
            Restaurant restaurant = restaurants.get(order.getId());
            Customer customer = customers.get(order.getId());

            RestaurantDto restaurantDto = new RestaurantDto();
            restaurantDto.setAddress(restaurant.getAddress().toString());
            restaurantDto.setDistance((double) restaurant.getDistance());
            deliveryDto.setRestaurant(restaurantDto);

            CustomerDto customerDto = new CustomerDto();
            customerDto.setAddress(customer.getAddress().toString());
            deliveryDto.setCustomer(customerDto);

            deliveries.add(deliveryDto);
        }
        return deliveries;
    }
}
