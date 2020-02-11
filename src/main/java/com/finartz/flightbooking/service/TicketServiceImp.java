package com.finartz.flightbooking.service;

import com.finartz.flightbooking.domain.dto.TicketBookingRequestDto;
import com.finartz.flightbooking.domain.dto.TicketBookingResponseDto;
import com.finartz.flightbooking.domain.entity.FlightBooking;
import com.finartz.flightbooking.domain.entity.Passenger;
import com.finartz.flightbooking.domain.entity.Ticket;
import com.finartz.flightbooking.exception.ErrorMessages;
import com.finartz.flightbooking.exception.FlightBookingException;
import com.finartz.flightbooking.exception.TicketNotFoundException;
import com.finartz.flightbooking.repository.TicketRepository;
import com.finartz.flightbooking.service.mapper.TicketMapper;
import com.finartz.flightbooking.service.validator.TicketServiceValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImp implements TicketService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TicketServiceImp.class);

    private final TicketRepository ticketRepository;

    private final FlightBookingService flightBookingService;

    private final PassengerService passengerService;

    private final TicketServiceValidator ticketServiceValidator;

    @Autowired
    public TicketServiceImp(TicketRepository ticketRepository,
                            FlightBookingService flightBookingService,
                            TicketServiceValidator ticketServiceValidator,
                            PassengerService passengerService
    ) {
        this.flightBookingService = flightBookingService;
        this.ticketRepository = ticketRepository;
        this.passengerService = passengerService;
        this.ticketServiceValidator = ticketServiceValidator;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public TicketBookingResponseDto createTicketBooking(TicketBookingRequestDto ticketBookingRequestDto) {

        LOGGER.info("ticket bookinf request info{}", ticketBookingRequestDto.toString());

        ticketServiceValidator.validateFlightBookingRequestDto(ticketBookingRequestDto);


        Integer numberOfSeat = ticketBookingRequestDto.getPassengerDtoList().size();

        List<FlightBooking> flightBoookingList = new ArrayList<>();
        if (ticketBookingRequestDto.getGoingFlightId() != null) {
            FlightBooking goingFlightBooking = flightBookingService.createFlightBooking(ticketBookingRequestDto.getGoingFlightId(), numberOfSeat, FlightBooking.FlightBookingType.GOING);
            flightBoookingList.add(goingFlightBooking);
        }

        if (ticketBookingRequestDto.getReturnFlightId() != null) {
            FlightBooking returnFlightBooking = flightBookingService.createFlightBooking(ticketBookingRequestDto.getReturnFlightId(), numberOfSeat, FlightBooking.FlightBookingType.RETURN);
            flightBoookingList.add(returnFlightBooking);
        }


        List<Passenger> passengerList = passengerService.savePassengerDtoList(ticketBookingRequestDto.getPassengerDtoList());

        Ticket ticket = new Ticket();
        ticket.setFlightBookingList(flightBoookingList);
        ticket.setPassengerList(passengerList);

        BigDecimal totalPrice = flightBoookingList.stream()
                .map(FlightBooking::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        ticket.setTotalPrice(totalPrice);
        ticket.setStatus(Ticket.TicketStatus.SUCCESS);

        ticketRepository.save(ticket);


        return TicketMapper.INSTANCE.convertToDto(ticket);
    }


    @Override
    @Transactional(readOnly =true, propagation = Propagation.SUPPORTS)
    public TicketBookingResponseDto getTicketByTicketNo(String ticketNo) {

        Optional<Ticket> ticket = ticketRepository.findFirstByTicketNoIgnoreCase(ticketNo);
        if (!ticket.isPresent()) {
            LOGGER.error("ticket Not Found:{}", ticketNo);
            throw new FlightBookingException(ErrorMessages.TICKET_SERVICE_TICKET_NOT_FOUND);
        }
        return TicketMapper.INSTANCE.convertToDto(ticket.get());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public TicketBookingResponseDto cancelTicketById(Long ticketId) {
        LOGGER.debug(" cancelTicketById ticketId:{}", ticketId);

        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(TicketNotFoundException::new);

        LOGGER.debug(" cancelTicketById ticketId2:{}", ticket.getId());
        LOGGER.debug("ticket.getFlightBookingList size :{}", ticket.getFlightBookingList().size());
        //cancel satus Booking
        for (FlightBooking flightBooking : ticket.getFlightBookingList()) {
            flightBookingService.cancelFlightBooking(flightBooking.getId());
        }

        ticket.setStatus(Ticket.TicketStatus.CANCELED);
        ticketRepository.save(ticket);

        return TicketMapper.INSTANCE.convertToDto(ticket);

        //TODO:Asynron process to Queue

    }
}
