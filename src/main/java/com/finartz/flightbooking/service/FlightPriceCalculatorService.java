package com.finartz.flightbooking.service;

import com.finartz.flightbooking.domain.entity.Flight;

import java.math.BigDecimal;

public interface FlightPriceCalculatorService {
    BigDecimal getPrice(Flight flight);
}
