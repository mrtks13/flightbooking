package com.finartz.flightbooking.service;

import com.finartz.flightbooking.domain.entity.Flight;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class FlightPriceCalculatorServiceImplTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(FlightPriceCalculatorServiceImplTest.class);

    @InjectMocks
    FlightPriceCalculatorServiceImpl flightPriceCalculatorService;

    @Test
    public void calculatePercentage() {
    }

    @Test
    public void getPrice() {
        Flight flight = new Flight();
        flight.setNumberOfTotalSeat(100);
        flight.setNumberOfBookingSeat(12);
        flight.setTicketPrice(new BigDecimal(100));
        flight.setLastTicketPrice(new BigDecimal(100));
        BigDecimal result = flightPriceCalculatorService.getPrice(flight);

        assertEquals(new BigDecimal(120), result);
    }

    @Test
    public void getNumberOfIncrementFloorSuccesful() {


        int numberOfBookingSeat = 5;
        int incrementSize = 10;

        int numberOfIncrement = new Double(Math.floor(numberOfBookingSeat / incrementSize)).intValue();

        assertEquals(0, numberOfIncrement);

    }

    @Test
    public void getNumberOfIncrement() {


        int numberOfBookingSeat = 38;
        int incrementSize = 5;

        int numberOfIncrement = new Double(Math.floor(numberOfBookingSeat / incrementSize)).intValue();

        assertEquals(7, numberOfIncrement);

    }


    @Test
    public void getticketPrice() {


        //Given
        BigDecimal ticketPrice = new BigDecimal(200);

        Integer incrementSize = 2;
        Integer incrementPricePercentege = 10;


        for (int time = 0; time < incrementSize; time++) {
            LOGGER.debug("time:{}", time);

            BigDecimal ticketPricePercentege = ticketPrice.divide(new BigDecimal(incrementPricePercentege)).setScale(2, BigDecimal.ROUND_HALF_UP);
            LOGGER.debug("ticketPricePercentege:{}", ticketPricePercentege);
            ticketPrice = ticketPrice.add(ticketPricePercentege);
        }

        LOGGER.debug("ticketPrice:{}", ticketPrice);

        assertEquals(new BigDecimal(242.00).setScale(2, BigDecimal.ROUND_HALF_UP), ticketPrice);

    }
}