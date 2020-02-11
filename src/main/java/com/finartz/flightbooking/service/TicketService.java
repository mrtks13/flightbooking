package com.finartz.flightbooking.service;

import com.finartz.flightbooking.domain.dto.TicketBookingRequestDto;
import com.finartz.flightbooking.domain.dto.TicketBookingResponseDto;
import com.finartz.flightbooking.domain.entity.FlightBooking;
import com.finartz.flightbooking.domain.entity.Ticket;

import java.util.List;

public interface TicketService {

    TicketBookingResponseDto createTicketBooking(TicketBookingRequestDto ticketBookingRequestDto);

    TicketBookingResponseDto getTicketByTicketNo(String ticketNo);

    TicketBookingResponseDto cancelTicketById(Long ticketId);


}
