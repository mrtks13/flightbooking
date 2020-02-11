package com.finartz.flightbooking.service;

import com.finartz.flightbooking.domain.dto.PassengerDto;
import com.finartz.flightbooking.domain.entity.Passenger;

import java.util.List;

public interface PassengerService {


    List<Passenger> savePassengerDtoList(List<PassengerDto> passengerDtoList);

}
