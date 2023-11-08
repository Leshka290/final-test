package ru.liga.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.liga.dto.enums.CourierStatus;
import ru.liga.entity.Courier;

import java.util.List;

public interface CourierRepository extends JpaRepository<Courier, Long> {
    List<Courier> getCouriersByStatus(CourierStatus courierStatus);
}
