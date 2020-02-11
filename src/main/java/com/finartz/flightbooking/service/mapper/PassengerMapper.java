package com.finartz.flightbooking.service.mapper;

import com.finartz.flightbooking.domain.dto.PassengerDto;
import com.finartz.flightbooking.domain.entity.Passenger;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PassengerMapper {
    PassengerMapper INSTANCE = Mappers.getMapper(PassengerMapper.class);

    Passenger convertToPassenger(PassengerDto dto);

    PassengerDto convertToPassengerDto(Passenger Passenger);


}
