package com.finartz.flightbooking.service;

import com.finartz.flightbooking.domain.dto.AirportDto;
import com.finartz.flightbooking.domain.entity.Airport;
import com.finartz.flightbooking.exception.ErrorMessages;
import com.finartz.flightbooking.exception.FlightBookingException;
import com.finartz.flightbooking.repository.AirportRepository;
import com.finartz.flightbooking.service.mapper.AirportMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class AirportServiceImpl implements AirportService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AirportServiceImpl.class);
    private final AirportRepository airportRepository;


    @Autowired
    public AirportServiceImpl(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Page<AirportDto> getAllAirportList(int page, int size, String sort) {

        Page<Airport> airportPage = airportRepository.findAll(PageRequest.of(page, size, Sort.by(sort)));
        Page<AirportDto> airportDtos = airportPage
                .map(this::convertToAirportDto);
        return airportDtos;
    }

    private AirportDto convertToAirportDto(Airport airport) {
        return AirportMapper.INSTANCE.convertToAirportDto(airport);
    }

    @Override
    public List<AirportDto> getAirportByName(String name) {
        if (StringUtils.isEmpty(name)) {
            throw new FlightBookingException(ErrorMessages.NAME_CAN_NOT_BE_NULL);
        }
        LOGGER.debug("name :{}", name);
        return airportRepository.findAirportsByName(name).stream()
                .map(this::convertToAirportDto)
                .collect(Collectors.toList());


    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public AirportDto saveAirport(AirportDto airportDto) {
        Assert.notNull(airportDto.getName(), ErrorMessages.NAME_CAN_NOT_BE_NULL);
        Airport airport = AirportMapper.INSTANCE.convertToAirport(airportDto);
        airportRepository.save(airport);


        return AirportMapper.INSTANCE.convertToAirportDto(airport);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Airport getAirportById(Long airportId) {
        return airportRepository.findById(airportId).orElseThrow(EntityNotFoundException::new);
    }
}
