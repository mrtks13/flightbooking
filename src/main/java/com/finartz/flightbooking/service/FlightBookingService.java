package com.finartz.flightbooking.service;

import com.finartz.flightbooking.domain.entity.FlightBooking;

public interface FlightBookingService {


    FlightBooking createFlightBooking(Long flightId, Integer numOfSeat, FlightBooking.FlightBookingType flightBookingType);

    void cancelFlightBooking(Long flightBookingId);


}
