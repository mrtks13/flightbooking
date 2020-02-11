package com.finartz.flightbooking.controller;

import com.finartz.flightbooking.domain.dto.FlightDto;
import com.finartz.flightbooking.domain.dto.FlightResultDto;
import com.finartz.flightbooking.domain.dto.FlightRouteDto;
import com.finartz.flightbooking.domain.dto.FlightSearchDto;
import com.finartz.flightbooking.domain.entity.Flight;
import com.finartz.flightbooking.domain.entity.FlightRoute;
import com.finartz.flightbooking.service.FlightRouteService;
import com.finartz.flightbooking.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("flight/")
public class FlightController {
    private final FlightService flightService;


    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }


    @GetMapping(value = "search/")
    public ResponseEntity<FlightResultDto> searcFlight(@RequestParam Long departureAirportId,
                                                       @RequestParam Long arrivalAirportId,
                                                       @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDateTime departureDate,
                                                       @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDateTime arrivalDate,
                                                       @RequestParam(required = false) Long airlineCompanyId,
                                                       @RequestParam Integer numberOfSeat,
                                                       @RequestParam(required = false, defaultValue = "false") boolean onlyGoing) {
        FlightSearchDto flightSearchDto = new FlightSearchDto(departureAirportId, arrivalAirportId, departureDate, arrivalDate, airlineCompanyId, numberOfSeat, onlyGoing);
        FlightResultDto flightResultDto = flightService.searchFlights(flightSearchDto);
        return new ResponseEntity<>(flightResultDto, HttpStatus.OK);
    }


}

