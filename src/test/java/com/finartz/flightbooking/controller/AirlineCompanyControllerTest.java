package com.finartz.flightbooking.controller;

import com.finartz.flightbooking.domain.dto.AirlineCompanyDto;
import com.finartz.flightbooking.domain.entity.AirlineCompany;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AirlineCompanyControllerTest {


    @Value("${server.servlet.context-path}")
    private String apiPath;
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private TestHelper testHelper;


    @Test
    void getAllAirlineCompanyList() {

        ResponseEntity<AirlineCompany[]> responseEntity = restTemplate.exchange(apiPath+"airlinecompanies/all", HttpMethod.GET, testHelper.getRequestHeaders(), AirlineCompany[].class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, responseEntity.getHeaders().getContentType());
    }

    @Test
    void getAirlineCompanyListByName() {

        ResponseEntity<AirlineCompany[]> responseEntity = restTemplate.exchange(apiPath+"airlinecompanies/search/thy", HttpMethod.GET, testHelper.getRequestHeaders(), AirlineCompany[].class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, responseEntity.getHeaders().getContentType());
    }


}