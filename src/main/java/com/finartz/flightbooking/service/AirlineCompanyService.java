package com.finartz.flightbooking.service;

import com.finartz.flightbooking.domain.dto.AirlineCompanyDto;
import com.finartz.flightbooking.domain.entity.AirlineCompany;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;


public interface AirlineCompanyService {

    List<AirlineCompany> getAllAirlineCompanyList();

    List<AirlineCompany> getAirlineCompanyListByName(String name);

    AirlineCompanyDto saveAirlineCompany(AirlineCompanyDto airlineCompanyDto);

}
