package com.finartz.flightbooking.service;

import com.finartz.flightbooking.domain.dto.FlightRouteDto;
import com.finartz.flightbooking.domain.entity.FlightRoute;

import java.util.List;

public interface FlightRouteService {

    FlightRouteDto SaveRoute(FlightRouteDto flightRouteDto);

    List<FlightRouteDto> search(FlightRouteDto flightRouteDto);
}
