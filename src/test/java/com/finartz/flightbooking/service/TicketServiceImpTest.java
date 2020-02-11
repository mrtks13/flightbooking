package com.finartz.flightbooking.service;

import com.finartz.flightbooking.domain.dto.PassengerDto;
import com.finartz.flightbooking.domain.dto.TicketBookingRequestDto;
import com.finartz.flightbooking.domain.dto.TicketBookingResponseDto;
import com.finartz.flightbooking.domain.entity.Flight;
import com.finartz.flightbooking.domain.entity.FlightBooking;
import com.finartz.flightbooking.domain.entity.Passenger;
import com.finartz.flightbooking.domain.entity.Ticket;
import com.finartz.flightbooking.repository.TicketRepository;
import com.finartz.flightbooking.service.validator.TicketServiceValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class TicketServiceImpTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(TicketServiceImpTest.class);

    @InjectMocks
    private TicketServiceImp ticketService;

    @Mock
    private FlightBookingService flightBookingService;

    @Mock
    private PassengerService passengerService;

    @Mock
    private TicketRepository ticketRepository;

    @Captor
    private ArgumentCaptor<Long> flightBookingIdCaptor;

    @Mock
    private TicketServiceValidator ticketServiceValidator;


    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void WhencreateTicketBooking_OK() {

        //Given
        Long goingFlightId = 1234585L;
        Long returnFlightId = 1252426L;
        String emaill = "muratakkus13@gmail.com";


        PassengerDto passengerDto = new PassengerDto();
        passengerDto.setEmail(emaill);

        List<PassengerDto> passengerDtoList = new ArrayList<>();
        passengerDtoList.add(passengerDto);

        TicketBookingRequestDto ticketBookingRequestDto = new TicketBookingRequestDto();
        ticketBookingRequestDto.setGoingFlightId(goingFlightId);
        ticketBookingRequestDto.setReturnFlightId(returnFlightId);
        ticketBookingRequestDto.setPassengerDtoList(passengerDtoList);


        Passenger passenger = new Passenger();
        passenger.setId(1L);
        passenger.setEmail(passengerDto.getEmail());

        List<Passenger> passengerList = new ArrayList<>();
        passengerList.add(passenger);

        Flight goingFlight = new Flight();
        goingFlight.setNumberOfBookingSeat(5);
        goingFlight.setFlightCode("F-01");
        goingFlight.setId(goingFlightId);
        goingFlight.setNumberOfTotalSeat(100);
        goingFlight.setTicketPrice(new BigDecimal(250));

        Flight returnFlight = new Flight();
        returnFlight.setNumberOfBookingSeat(10);
        returnFlight.setFlightCode("F-01");
        returnFlight.setId(returnFlightId);
        returnFlight.setNumberOfTotalSeat(100);
        returnFlight.setTicketPrice(new BigDecimal(200));


        FlightBooking flightBooking = new FlightBooking();
        flightBooking.setFlight(goingFlight);
        flightBooking.setTotalPrice(new BigDecimal(250));
        flightBooking.setCountOfSeat(passengerDtoList.size());
        flightBooking.setFlightBookingType(FlightBooking.FlightBookingType.GOING);

        FlightBooking returnFlightBooking = new FlightBooking();
        returnFlightBooking.setFlight(returnFlight);
        returnFlightBooking.setTotalPrice(new BigDecimal(200));
        returnFlightBooking.setCountOfSeat(passengerDtoList.size());
        returnFlightBooking.setFlightBookingType(FlightBooking.FlightBookingType.GOING);


        doNothing().when(ticketServiceValidator).validateFlightBookingRequestDto(ticketBookingRequestDto);

        when(passengerService.savePassengerDtoList(anyList())).thenReturn(passengerList);

        when(flightBookingService.createFlightBooking(goingFlightId, passengerDtoList.size(), FlightBooking.FlightBookingType.GOING)).thenReturn(flightBooking);

        when(flightBookingService.createFlightBooking(returnFlightId, passengerDtoList.size(), FlightBooking.FlightBookingType.RETURN)).thenReturn(returnFlightBooking);

        //when
        TicketBookingResponseDto resultDto = ticketService.createTicketBooking(ticketBookingRequestDto);


        verify(ticketServiceValidator, times(1)).validateFlightBookingRequestDto(any());

        verify(passengerService, times(1)).savePassengerDtoList(anyList());

        verify(flightBookingService, times(2)).createFlightBooking(any(),any(),any());
        //then
        assertEquals(1, passenger.getId());
        assertEquals(new BigDecimal(450), resultDto.getTotalPrice());
    }

    @Test
    public void getTicketByTicketNo() {

        String ticketNo = "THY-123";

    }

    @Test
    public void whenTicketById_ThenCancelTicketByNo() {

        //given
        Ticket ticket = new Ticket();
        Long ticketId = 123L;
        Long returnFlightBookingId = 2L;
        Long goingFlightBookingId = 1L;
        ticket.setId(ticketId);

        FlightBooking returnFlightBooking = new FlightBooking();
        returnFlightBooking.setId(returnFlightBookingId);

        FlightBooking goingFlightBooking = new FlightBooking();
        goingFlightBooking.setId(goingFlightBookingId);

        List<FlightBooking> flightBookingList = new ArrayList<>();

        flightBookingList.add(returnFlightBooking);
        flightBookingList.add(goingFlightBooking);
        ticket.setFlightBookingList(flightBookingList);


        when(ticketRepository.findById(ticketId)).thenReturn(Optional.of(ticket));

        //when
        TicketBookingResponseDto ticketBookingResponseDto = ticketService.cancelTicketById(ticket.getId());

        //verify
 /*       Mockito.verify(flightBookingService, times(2)).cancelFlightBooking(flightBookingIdCaptor.capture());

        //then
        assertEquals(returnFlightBookingId, flightBookingIdCaptor.getAllValues().get(0).longValue());
        assertEquals(goingFlightBookingId, flightBookingIdCaptor.getAllValues().get(1).longValue())*/
        ;
        assertEquals(Ticket.TicketStatus.CANCELED.name(), ticketBookingResponseDto.getStatus());

    }
}