package com.finartz.flightbooking.service;

import com.finartz.flightbooking.domain.entity.Flight;
import com.finartz.flightbooking.domain.entity.FlightBooking;
import com.finartz.flightbooking.exception.ErrorMessages;
import com.finartz.flightbooking.exception.FlightBookingException;
import com.finartz.flightbooking.repository.FlightBookingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class FlightBookingServiceImpl implements FlightBookingService {


    private static final Logger LOGGER = LoggerFactory.getLogger(FlightBookingServiceImpl.class);
    private final FlightBookingRepository flightBookingRepository;

    private final FlightService flightService;


    @Autowired
    public FlightBookingServiceImpl(FlightBookingRepository flightBookingRepository,
                                    FlightService flightService
    ) {
        this.flightBookingRepository = flightBookingRepository;
        this.flightService = flightService;
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public FlightBooking createFlightBooking(Long flightId, Integer numOfSeat, FlightBooking.FlightBookingType flightBookingType) {

        Flight flight = flightService.getFlightById(flightId);

        if (!isAvailable(flight, numOfSeat)) {
            throw new FlightBookingException(ErrorMessages.FLIGT_IS_FULL);
        }
        FlightBooking flightBooking = new FlightBooking();
        flightBooking.setFlight(flight);
        flightBooking.setFlightBookingType(flightBookingType);
        flightBooking.setTotalPrice(getBookingPrice(flight, numOfSeat));
        flightBooking.setCountOfSeat(numOfSeat);

        flightBookingRepository.save(flightBooking);
        flightService.saveLastBooking(flight, numOfSeat);

        return flightBooking;
    }

    private Boolean isAvailable(Flight flight, Integer getNumberOfSeat) {

        return (flight.getNumberOfTotalSeat() - flight.getNumberOfBookingSeat() + getNumberOfSeat) > 0;

    }

    private BigDecimal getBookingPrice(Flight flight, Integer getNumberOfSeat) {

        return flight.getLastTicketPrice().multiply(new BigDecimal(getNumberOfSeat)).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void cancelFlightBooking(Long flightBookingId) {

        FlightBooking flightBooking = flightBookingRepository.getOne(flightBookingId);

        flightService.cancelLastBooking(flightBooking.getFlight().getId(), flightBooking.getCountOfSeat());
        flightBooking.setFlightBookingStatus(FlightBooking.FlightBookingStatus.CANCELLED);

        flightBookingRepository.save(flightBooking);


    }
}
