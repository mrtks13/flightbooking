package com.finartz.flightbooking.service;

import com.finartz.flightbooking.domain.dto.PassengerDto;
import com.finartz.flightbooking.domain.entity.Passenger;
import com.finartz.flightbooking.repository.PassengerRepository;
import com.finartz.flightbooking.service.mapper.PassengerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PassengerServiceImpl implements PassengerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FlightServiceImpl.class);
    private final PassengerRepository passengerRepository;


    @Autowired
    public PassengerServiceImpl(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }


    @Override
    public List<Passenger> savePassengerDtoList(List<PassengerDto> passengerDtoList) {

        List<Passenger> passengerList = passengerDtoList.stream().map(this::convertToPassenger).collect(Collectors.toList());

        passengerRepository.saveAll(passengerList);

        return passengerList;
    }

    private Passenger convertToPassenger(PassengerDto passengerDto) {

        return PassengerMapper.INSTANCE.convertToPassenger(passengerDto);
    }


}
