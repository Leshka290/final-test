package ru.liga.mapper;

import org.mapstruct.Mapper;
import ru.liga.dto.RestaurantDto;
import ru.liga.dto.RestaurantNameDto;
import ru.liga.entity.Address;
import ru.liga.entity.Restaurant;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {

    String map(Address address);
    RestaurantDto restaurantToRestaurantDto(Restaurant restaurant);
    RestaurantNameDto restaurantToRestaurantNameDto(Restaurant restaurant);
}
