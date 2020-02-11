package com.finartz.flightbooking.service;

import com.finartz.flightbooking.domain.entity.Flight;
import com.finartz.flightbooking.domain.entity.FlightBooking;
import com.finartz.flightbooking.repository.FlightBookingRepository;
import com.finartz.flightbooking.service.validator.TicketServiceValidator;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class FlightBookingServiceImplTest {


    @InjectMocks
    private FlightBookingServiceImpl flightBookingService;

    @Mock
    private PassengerService passengerService;
    @Mock
    private FlightBookingRepository flightBookingRepository;

    @Mock
    private FlightPriceCalculatorService priceCalculatorService;

    @Mock
    private FlightService flightService;

    @Mock
    TicketServiceValidator ticketServiceValidator;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    void whenRoundWay_thenCreateFlightBooking_OK() {
        //Given

        Long goingFlightId = 1234585L;
        Long returnFlightId = 1252426L;
        BigDecimal goingFlightTicketPrice = new BigDecimal(250.00);
        BigDecimal returnFlightTicketPrice = new BigDecimal(200.00);


        Flight goingFlight = new Flight();
        goingFlight.setNumberOfBookingSeat(5);
        goingFlight.setFlightCode("F-01");
        goingFlight.setId(goingFlightId);
        goingFlight.setNumberOfTotalSeat(100);
        goingFlight.setTicketPrice(goingFlightTicketPrice);

        Flight returnFlight = new Flight();
        returnFlight.setNumberOfBookingSeat(10);
        returnFlight.setFlightCode("F-01");
        returnFlight.setId(returnFlightId);
        returnFlight.setNumberOfTotalSeat(100);
        returnFlight.setTicketPrice(returnFlightTicketPrice);

        //when(passengerService.getPassenger(flightBookingRequestDto.getPassengerDto())).thenReturn(passenger);

        when(flightService.getFlightById(goingFlightId)).thenReturn(goingFlight);
        when(flightService.getFlightById(returnFlightId)).thenReturn(returnFlight);


        // when
        FlightBooking goingBooking = flightBookingService.createFlightBooking(goingFlightId, 1, FlightBooking.FlightBookingType.GOING);
        FlightBooking returnBooking = flightBookingService.createFlightBooking(returnFlightId, 1, FlightBooking.FlightBookingType.RETURN);

        //then
        Assert.assertEquals(goingFlightTicketPrice.setScale(2, RoundingMode.HALF_UP), goingBooking.getTotalPrice());

        Assert.assertEquals(returnFlightTicketPrice.setScale(2, RoundingMode.HALF_UP), returnBooking.getTotalPrice());
        // assertEquals(940, resultDto.getTotalPrice());


    }

    @Test
    void when_() {
    }


}