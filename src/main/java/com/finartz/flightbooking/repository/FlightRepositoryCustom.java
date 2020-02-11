package com.finartz.flightbooking.repository;

import com.finartz.flightbooking.domain.dto.FlightSearchDto;
import com.finartz.flightbooking.domain.entity.Flight;
import org.springframework.data.domain.Page;

import java.util.List;

public interface FlightRepositoryCustom {

    List<Flight> searchFlights(FlightSearchDto flightSearchDto);
}
