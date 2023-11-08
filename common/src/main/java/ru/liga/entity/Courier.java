package ru.liga.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.liga.dto.enums.CourierStatus;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "couriers")
public class Courier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "courier_id", nullable = false)
    private long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "phone", nullable = false)
    private String phone;
    @Column(name = "coordinates", nullable = false)
    private int coordinates;
    @Enumerated(EnumType.STRING)
    private CourierStatus status;
}
