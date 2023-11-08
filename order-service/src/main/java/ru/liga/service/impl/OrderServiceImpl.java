package ru.liga.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.liga.dto.CreatedOrderDto;
import ru.liga.dto.CreatedOrderToDeliveryDto;
import ru.liga.dto.OrderDto;
import ru.liga.dto.enums.OrderStatus;
import ru.liga.entity.Customer;
import ru.liga.entity.Order;
import ru.liga.entity.OrderItem;
import ru.liga.entity.Restaurant;
import ru.liga.exception.CustomerNotFoundException;
import ru.liga.exception.OrderNotFoundException;
import ru.liga.exception.RestaurantNotFoundException;
import ru.liga.mapper.OrderMapper;
import ru.liga.repository.CustomerRepository;
import ru.liga.repository.OrderItemRepository;
import ru.liga.repository.OrderRepository;
import ru.liga.repository.RestaurantRepository;
import ru.liga.service.OrderItemService;
import ru.liga.service.OrderService;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderItemService orderItemService;
    private final OrderMapper orderMapper;

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CustomerRepository customerRepository;
    private final RestaurantRepository restaurantRepository;

    @Override
    public CreatedOrderToDeliveryDto createOrder(CreatedOrderDto createOrderDto, Long customerId) {
        log.info("Current method is - createOrder");

        Restaurant restaurant = restaurantRepository.findById(createOrderDto.getRestaurantId())
                .orElseThrow(RestaurantNotFoundException::new);
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(CustomerNotFoundException::new);

        Order order = new Order();

        List<OrderItem> orderItems = orderItemService.createOrderItems(order, createOrderDto);
        order.setItems(orderItems);

        order.setStatus(OrderStatus.CUSTOMER_CREATED);
        order.setRestaurant(restaurant);
        order.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
        order.setCustomer(customer);

        orderRepository.save(order);
        orderItemRepository.saveAll(orderItems);

        CreatedOrderToDeliveryDto createdOrderToDeliveryDto = new CreatedOrderToDeliveryDto();
        createdOrderToDeliveryDto.setSecretPaymentUrl(createUrlForOrderPayment());
        createdOrderToDeliveryDto.setEstimatedTimeOfArrival(LocalDateTime.now().plusHours(1));
        createdOrderToDeliveryDto.setId(order.getId());
        return createdOrderToDeliveryDto;
    }

    private String createUrlForOrderPayment() {
        String protocol = "http";
        String host = "localhost";
        int port = 8080;
        String path = "/payment/" + generateRandomInt();
        try {
            URL url = new URL(protocol, host, port, path);
            return url.toString();
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private int generateRandomInt() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = random.generateSeed(200);
        random.nextBytes(bytes);
        return random.nextInt(Integer.MAX_VALUE);
    }

    @Override
    public OrderDto getOrderById(Long orderId) {
        log.info("Current method is - getOrderById");

        Order order = orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
        return orderMapper.orderToOrderDto(order);
    }

    @Override
    public void updateOrderStatusById(Long orderId, OrderStatus status) {
        Order order = orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
        order.setStatus(status);
        orderRepository.save(order);
    }

    @Override
    public List<OrderDto> getAllOrders() {
        log.info("Current method is - getAllOrders");
        List<Order> orders = orderRepository.findAll();

        return orderMapper.ordersToOrderDto(orders);
    }
}
