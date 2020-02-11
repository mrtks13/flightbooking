package com.finartz.flightbooking.service.validator;

import com.finartz.flightbooking.domain.dto.TicketBookingRequestDto;

public interface TicketServiceValidator {
    void validateFlightBookingRequestDto(TicketBookingRequestDto ticketBookingRequestDto);
}
