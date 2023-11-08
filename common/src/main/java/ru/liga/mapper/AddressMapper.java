package ru.liga.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.liga.dto.AddressDto;
import ru.liga.entity.Address;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    @Mapping(target = "address", expression = "java(address.toString())")
    AddressDto addressToAddressDto(Address address);
}
