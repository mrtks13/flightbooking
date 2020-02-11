package com.finartz.flightbooking.service.mapper;

import com.finartz.flightbooking.domain.dto.AirlineCompanyDto;
import com.finartz.flightbooking.domain.dto.AirportDto;
import com.finartz.flightbooking.domain.entity.AirlineCompany;
import com.finartz.flightbooking.domain.entity.Airport;
import com.finartz.flightbooking.domain.entity.FlightRoute;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AirportMapper {
    AirportMapper INSTANCE = Mappers.getMapper(AirportMapper.class);

    Airport convertToAirport(AirportDto dto);

    AirportDto convertToAirportDto(Airport entity);

}
