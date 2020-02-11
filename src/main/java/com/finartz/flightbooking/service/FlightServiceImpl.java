package com.finartz.flightbooking.service;

import com.finartz.flightbooking.domain.dto.FlightResultDto;
import com.finartz.flightbooking.domain.dto.FlightSearchDto;
import com.finartz.flightbooking.domain.entity.Flight;
import com.finartz.flightbooking.repository.FlightRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;

@Service
public class FlightServiceImpl implements FlightService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FlightServiceImpl.class);
    private final FlightRepository flightRepository;
    private final FlightPriceCalculatorService priceCalculatorService;

    @Autowired
    public FlightServiceImpl(FlightRepository flightRepository,
                             @Qualifier("perPercentege") FlightPriceCalculatorService priceCalculatorService
    ) {
        this.flightRepository = flightRepository;
        this.priceCalculatorService = priceCalculatorService;
    }


    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Flight getFlightById(Long id) {
        return flightRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Flight save(Flight flight) {

        return flightRepository.save(flight);
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveLastBooking(Flight flight, Integer numberOfBookingSeat) {

        synchronized (flight.getId()) {
            flight.setNumberOfBookingSeat(flight.getNumberOfBookingSeat() + numberOfBookingSeat);
            BigDecimal flightLastTicketPrice = priceCalculatorService.getPrice(flight);
            flight.setLastTicketPrice(flightLastTicketPrice);
        }


        flightRepository.save(flight);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void cancelLastBooking(Long flightId, Integer numberOfBookingSeat) {

        Flight flight = getFlightById(flightId);
        synchronized (flight.getId()) {
            flight.setNumberOfBookingSeat(flight.getNumberOfBookingSeat() - numberOfBookingSeat);
            BigDecimal flightLastTicketPrice = priceCalculatorService.getPrice(flight);
            flight.setLastTicketPrice(flightLastTicketPrice);
        }
        flightRepository.save(flight);
    }

    @Override
    public FlightResultDto searchFlights(FlightSearchDto flightSearchDto) {


        return null;

    }
}
