package com.finartz.flightbooking.service.validator;

import com.finartz.flightbooking.domain.dto.TicketBookingRequestDto;
import com.finartz.flightbooking.exception.ErrorMessages;
import com.finartz.flightbooking.exception.ValidationException;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class TicketServiceValidatorImpl implements TicketServiceValidator {

    @Override
    public void validateFlightBookingRequestDto(TicketBookingRequestDto ticketBookingRequestDto) {
        if(CollectionUtils.isEmpty(ticketBookingRequestDto.getPassengerDtoList())) {
            throw new ValidationException(ErrorMessages.TICKET_SERVICE_PASSENGER_LIST_CAN_NOT_BE_EMPTY);
        }

        if(ticketBookingRequestDto.getGoingFlightId() == null || ticketBookingRequestDto.getGoingFlightId() == 0) {
            throw new ValidationException(ErrorMessages.TICKET_SERVICE_REQEST_CAN_NOT_BE_NULL);
        }

    }
}
