package ru.liga.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id", nullable = false)
    private long id;
    @Column(name = "city", nullable = false)
    private String city;
    @Column(name = "street", nullable = false)
    private String street;
    @Column(name = "building", nullable = false)
    private String building;

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", building='" + building + '\'' +
                '}';
    }
}
