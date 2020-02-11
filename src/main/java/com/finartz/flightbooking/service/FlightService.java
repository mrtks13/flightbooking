package com.finartz.flightbooking.service;

import com.finartz.flightbooking.domain.dto.FlightDto;
import com.finartz.flightbooking.domain.dto.FlightResultDto;
import com.finartz.flightbooking.domain.dto.FlightSearchDto;
import com.finartz.flightbooking.domain.entity.Flight;
import org.springframework.data.domain.Page;

import java.util.List;

public interface FlightService {


    void saveLastBooking(Flight flight, Integer numberOfBookingSeat);

    void cancelLastBooking(Long flightId, Integer numberOfBookingSeat);

    FlightResultDto searchFlights(FlightSearchDto flightSearchDto);

    Flight getFlightById(Long id);

    Flight save(Flight flight);

}


