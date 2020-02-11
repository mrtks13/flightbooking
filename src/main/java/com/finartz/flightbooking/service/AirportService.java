package com.finartz.flightbooking.service;

import com.finartz.flightbooking.domain.dto.AirportDto;
import com.finartz.flightbooking.domain.entity.Airport;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AirportService {

    Page<AirportDto> getAllAirportList(int page, int size, String sort);

    List<AirportDto> getAirportByName(String name);

    AirportDto saveAirport(AirportDto airportDto);

    Airport getAirportById(Long airportId);
}
