package com.finartz.flightbooking.controller;

import com.finartz.flightbooking.domain.dto.FlightRouteDto;
import com.finartz.flightbooking.service.FlightRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("v1/")
public class FlightRouteController {
    private final FlightRouteService flightRouteService;


    @Autowired
    public FlightRouteController(FlightRouteService flightRouteService) {
        this.flightRouteService = flightRouteService;
    }

    @GetMapping(value="flightroutes/",params = {"departureAirportId, arrivalAirportId"})
    @ResponseStatus(HttpStatus.OK)
    public List<FlightRouteDto> searcFlightRoute(@RequestParam("departureAirportId") Long departureAirportId, @RequestParam("arrivalAirportId") Long arrivalAirportId) {
        return flightRouteService.search(new FlightRouteDto(departureAirportId, arrivalAirportId));
    }

    @PostMapping(value="flightroutes/")
    @ResponseStatus(HttpStatus.CREATED)
    public FlightRouteDto saveFlightRoute(@RequestBody @Valid FlightRouteDto flightRouteDto) {
        return flightRouteService.SaveRoute(flightRouteDto);
    }
}
