package ru.liga.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.liga.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
