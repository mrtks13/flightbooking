package com.finartz.flightbooking.controller;

import com.finartz.flightbooking.domain.dto.AirportDto;
import com.finartz.flightbooking.service.AirportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@RestController
public class AirportController {
    private final AirportService airportService;
    private static final Logger LOGGER = LoggerFactory.getLogger(AirportController.class);

    private static class AirportControllerException extends RuntimeException {
        private AirportControllerException(String message) {
            super(message);
        }
    }

    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }


    @GetMapping(value = "airports/", params = {"page", "size", "sort"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Page<AirportDto>> getAllAirPortList(
            @RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("sort") String sort
    ) {
        Page<AirportDto> resultPage = airportService.getAllAirportList(page, size, sort);

        if (page > resultPage.getTotalPages()) {
            LOGGER.error("Page not Found:{}", page);
            throw new AirportControllerException("Page not Found");
        }

        return new ResponseEntity<>(resultPage, HttpStatus.OK);
    }

    @GetMapping(value = "airports/{name}")
    public ResponseEntity<List<AirportDto>> getAirlineCompanyListByName(@PathVariable("name") @NotEmpty String name) {
        return new ResponseEntity<>(airportService.getAirportByName(name), HttpStatus.OK);
    }

    @PostMapping(value = "airports/")
    public ResponseEntity<AirportDto> saveAirlineCompany(@RequestBody @Valid AirportDto airportDto) {
        return new ResponseEntity<AirportDto>(airportService.saveAirport(airportDto), HttpStatus.OK);
    }
}
