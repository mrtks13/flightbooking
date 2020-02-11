package com.finartz.flightbooking.service;

import com.finartz.flightbooking.domain.dto.PassengerDto;
import com.finartz.flightbooking.domain.entity.Passenger;
import com.finartz.flightbooking.repository.PassengerRepository;
import org.hamcrest.core.AnyOf;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Any;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class PassengerServiceImplTest {

    @InjectMocks
    private PassengerServiceImpl passengerService;

    @Mock
    private PassengerRepository passengerRepository;


    @Test
    public void when_savePassengerDtoList_OK() {

        List<PassengerDto> passengerDtoList = new ArrayList<>();
        PassengerDto passengerDto = new PassengerDto();
        passengerDto.setEmail("murat Akkus");

        passengerDtoList.add(passengerDto);

        passengerService.savePassengerDtoList(passengerDtoList);

        Mockito.verify(passengerRepository,Mockito.times(1)).saveAll(Mockito.anyCollection());






    }
}