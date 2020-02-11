package com.finartz.flightbooking.controller;

import com.finartz.flightbooking.domain.dto.AirlineCompanyDto;
import com.finartz.flightbooking.domain.entity.AirlineCompany;
import com.finartz.flightbooking.service.AirlineCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@RestController
@RequestMapping("airlinecompanies/")
public class AirlineCompanyController {
    private final AirlineCompanyService airlineCompanyService;


    @Autowired
    public AirlineCompanyController(AirlineCompanyService airlineCompanyService) {
        this.airlineCompanyService = airlineCompanyService;
    }

    @GetMapping(value = "all", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<AirlineCompany>> getAllAirlineCompanyList() {
        List<AirlineCompany> airlineCompanyList = airlineCompanyService.getAllAirlineCompanyList();
        return new ResponseEntity<>(airlineCompanyList, HttpStatus.OK);
    }

    @GetMapping(value = "search/{name}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<AirlineCompany>> getAirlineCompanyListByName(@PathVariable @NotEmpty String name) {
        return new ResponseEntity<>(airlineCompanyService.getAirlineCompanyListByName(name), HttpStatus.OK);
    }

    @PostMapping(value = "save", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<AirlineCompanyDto> saveAirlineCompany(@RequestBody @Valid AirlineCompanyDto airlineCompanyDto) {
        return new ResponseEntity<>(airlineCompanyService.saveAirlineCompany(airlineCompanyDto), HttpStatus.CREATED);
    }
}
