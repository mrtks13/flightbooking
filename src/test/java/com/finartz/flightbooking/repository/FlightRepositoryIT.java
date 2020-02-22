package com.finartz.flightbooking.repository;

import com.finartz.flightbooking.domain.dto.FlightSearchDto;
import com.finartz.flightbooking.domain.entity.Airport;
import com.finartz.flightbooking.domain.entity.Flight;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@EnableJpaRepositories(basePackageClasses = FlightRepository.class)
public class FlightRepositoryIT {

    @Autowired
    private FlightRepository FlightRepository;

    @Before
    public void setUp() throws Exception {


    }


    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void WhenRoundWay_ThensearchFlights_OK() {

        String departureDateStr = "2020-01-12";
        String returnDateDateStr = "2020-01-15";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        LocalDateTime departureDate = LocalDateTime.parse(departureDateStr, formatter);
        LocalDateTime returnDate = LocalDateTime.parse(returnDateDateStr, formatter);
        Long departureAirportId = 1L;
        Long arrivalAirportId = 2L;

        Long airlineCompanyId = null;
        Integer numberOfSeat = 2;
        Boolean onlyGoing = false;


        FlightSearchDto flightSearchDto = new FlightSearchDto(departureAirportId, arrivalAirportId, departureDate, returnDate, airlineCompanyId, numberOfSeat, onlyGoing);

        List<Flight> airportList = FlightRepository.searchFlights(flightSearchDto);
        Assert.assertEquals(0, airportList.size());
    }
}