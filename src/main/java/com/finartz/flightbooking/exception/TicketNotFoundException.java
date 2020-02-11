package com.finartz.flightbooking.exception;

public class TicketNotFoundException  extends RuntimeException{
    public TicketNotFoundException() {
        super(ErrorMessages.TICKET_SERVICE_TICKET_NOT_FOUND);

    }
}
