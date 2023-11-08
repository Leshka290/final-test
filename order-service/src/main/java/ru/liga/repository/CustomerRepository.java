package ru.liga.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.liga.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
