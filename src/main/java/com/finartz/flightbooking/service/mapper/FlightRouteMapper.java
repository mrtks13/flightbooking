package com.finartz.flightbooking.service.mapper;

import com.finartz.flightbooking.domain.dto.AirportDto;
import com.finartz.flightbooking.domain.dto.FlightRouteDto;
import com.finartz.flightbooking.domain.entity.Airport;
import com.finartz.flightbooking.domain.entity.FlightRoute;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {AirportMapper.class})
public interface FlightRouteMapper {

    FlightRouteMapper INSTANCE = Mappers.getMapper(FlightRouteMapper.class);


    @Mapping(source = "departure.name", target = "departureAirportName")
    @Mapping(source = "arrival.name", target = "arrivalAirportName")
    @Mapping(source = "departure.id", target = "departureAirportId")
    @Mapping(source = "arrival.id", target = "arrivalAirportId")
    FlightRouteDto convertToFlightRouteDto(FlightRoute entity);
}
