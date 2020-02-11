package com.finartz.flightbooking.service;

import com.finartz.flightbooking.domain.entity.Flight;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.function.Predicate;

import static java.lang.Math.round;

@Service("perPercentege")
public class FlightPriceCalculatorServiceImpl implements FlightPriceCalculatorService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FlightPriceCalculatorServiceImpl.class);

    private static final Integer incrementSize = 10;

    private static final Integer incrementPricePercentege = 10;

    @Override
    public  BigDecimal getPrice(Flight flight) {
        return calculatePrice(flight.getTicketPrice(), flight.getNumberOfBookingSeat());
    }


    private BigDecimal calculatePrice(BigDecimal ticketPrice, Integer numberOfBookingSeat) {
        Integer numberOfBookingIncrement = getNumberOfIncrement(numberOfBookingSeat);
        LOGGER.debug("count price incrementNumber:{}", numberOfBookingIncrement);
        return getLastPrice(numberOfBookingIncrement, ticketPrice);

    }

    private Integer getNumberOfIncrement(Integer numberOfBookingSeat) {
        return new Double(Math.floor(numberOfBookingSeat / incrementSize)).intValue();

    }

    private BigDecimal getLastPrice(Integer numberOfBookingIncrement, BigDecimal ticketPrice) {

        for (int time = 0; time < numberOfBookingIncrement; time++) {
            LOGGER.debug("time:{}", time);

            BigDecimal ticketPricePercentege = ticketPrice.divide(new BigDecimal(incrementPricePercentege)).setScale(2, BigDecimal.ROUND_HALF_UP);
            LOGGER.debug("ticketPricePercentege:{}", ticketPricePercentege);
            ticketPrice = ticketPrice.add(ticketPricePercentege);
        }

        LOGGER.debug("ticketPrice:{}", ticketPrice);
        return ticketPrice;
    }


}
