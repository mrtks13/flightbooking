package com.finartz.flightbooking.service;

import com.finartz.flightbooking.domain.dto.FlightRouteDto;
import com.finartz.flightbooking.domain.entity.Airport;
import com.finartz.flightbooking.domain.entity.FlightRoute;
import com.finartz.flightbooking.repository.FlightRouteRepository;
import com.finartz.flightbooking.service.mapper.FlightRouteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightRouteServiceImpl implements FlightRouteService {

    private final AirportService airportService;
    private final FlightRouteRepository flightRouteRepository;

    @Autowired
    public FlightRouteServiceImpl(AirportService airportService,
                                  FlightRouteRepository flightRouteRepository) {
        this.airportService = airportService;
        this.flightRouteRepository = flightRouteRepository;

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public FlightRouteDto SaveRoute(FlightRouteDto flightRouteDto) {


        Airport departureAirport = airportService.getAirportById(flightRouteDto.getDepartureAirportId());
        Airport arrivalAirport = airportService.getAirportById(flightRouteDto.getArrivalAirportId());
        FlightRoute flightRoute = new FlightRoute(departureAirport, arrivalAirport);
        flightRouteRepository.save(flightRoute);
        return FlightRouteMapper.INSTANCE.convertToFlightRouteDto(flightRoute);
    }

    @Override
    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    public List<FlightRouteDto> search(FlightRouteDto flightRouteDto) {
        return this.flightRouteRepository.
                findAllByArrivalAndDeparture_Id(flightRouteDto.getArrivalAirportId(),
                        flightRouteDto.getDepartureAirportId()).stream()
                .map(flightRoute -> FlightRouteMapper.INSTANCE.convertToFlightRouteDto(flightRoute)).collect(Collectors.toList());

    }
}
