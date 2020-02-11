package com.finartz.flightbooking.controller;

import com.finartz.flightbooking.domain.dto.*;
import com.finartz.flightbooking.service.FlightService;
import com.finartz.flightbooking.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("flight/")
public class TicketController {
    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }


    @PostMapping(value = "save", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<TicketBookingResponseDto> makeBooking(@RequestBody @Valid TicketBookingRequestDto ticketBookingRequestDto) {
        return new ResponseEntity<>(ticketService.createTicketBooking(ticketBookingRequestDto), HttpStatus.CREATED);
    }

    @GetMapping(value = "search/{ticketNo}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<TicketBookingResponseDto> getTicketByTicketNo(@PathVariable("ticketNo") @NotEmpty String ticketNo) {
        return new ResponseEntity<>(ticketService.getTicketByTicketNo(ticketNo), HttpStatus.OK);
    }

    @PutMapping(value = "cancel/{ticketid}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<TicketBookingResponseDto> getAirlineCompanyListByName(@PathVariable("ticketid") @NotEmpty Long ticketId) {
        return new ResponseEntity<>(ticketService.cancelTicketById(ticketId), HttpStatus.OK);
    }

}

