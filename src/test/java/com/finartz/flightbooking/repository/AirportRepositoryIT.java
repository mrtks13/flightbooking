package com.finartz.flightbooking.repository;

import com.finartz.flightbooking.domain.entity.Airport;
import org.junit.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.event.annotation.BeforeTestExecution;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@EnableJpaRepositories(basePackageClasses = AirportRepository.class)
@EntityScan(basePackageClasses = Airport.class)
public class AirportRepositoryIT {

    @Autowired
    private AirportRepository airportRepository;

    @Before
    public void setUp() throws Exception {
        Airport airport = new Airport();
        airport.setCountryCode("90");
        airport.setIataCode("SVH");
        airport.setName("Sabiha Gökçen");
        airportRepository.save(airport);

        Airport airport1 = new Airport();
        airport1.setCountryCode("90");
        airport1.setIataCode("SVH");
        airport1.setName("Istanbul");
        airportRepository.save(airport1);

        Airport airport2 = new Airport();
        airport2.setCountryCode("35");
        airport2.setIataCode("LND");
        airport2.setName("Londa airport");
        airportRepository.save(airport2);


    }

    @Test
    public void WhenAll_ThenfindAirportByNameContainingOrderByName() {

        List<Airport> airportList = airportRepository.findAirportsByName("Istanbul");
        Assert.assertEquals(1, airportList.size());
    }

    @Test
    public void WhenSmal_thenfindAirportByNameContainingOrderByNameAsc() {

        List<Airport> airportList = airportRepository.findAirportsByName("Ist");
        Assert.assertEquals(1, airportList.size());
    }

    @Test
    public void WhenExists_ThenfindAirportsByIataCodeEquals() {

        String iataCode = "LND";
        List<Airport> airportList = airportRepository.findAirportsByIataCodeEquals(iataCode);
        Assert.assertEquals(1, airportList.size());
        Assert.assertEquals(iataCode, airportList.get(0).getIataCode());
    }

    @Test
    public void when_findAirportsByIataCodeEquals_NotFound() {

        List<Airport> airportList = airportRepository.findAirportsByIataCodeEquals("RD");
        Assert.assertEquals(0, airportList.size());
    }


    @After
    public void tearDown() throws Exception {
        airportRepository.deleteAllInBatch();
    }
}