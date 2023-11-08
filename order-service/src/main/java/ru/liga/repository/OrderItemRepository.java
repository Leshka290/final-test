package ru.liga.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.liga.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
