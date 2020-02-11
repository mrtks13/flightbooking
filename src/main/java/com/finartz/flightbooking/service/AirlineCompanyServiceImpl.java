package com.finartz.flightbooking.service;

import com.finartz.flightbooking.domain.dto.AirlineCompanyDto;
import com.finartz.flightbooking.domain.entity.AirlineCompany;
import com.finartz.flightbooking.exception.ErrorMessages;
import com.finartz.flightbooking.exception.FlightBookingException;
import com.finartz.flightbooking.repository.AirlineCompanyRepository;
import com.finartz.flightbooking.service.mapper.AirlineCompanyMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class AirlineCompanyServiceImpl implements AirlineCompanyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AirlineCompanyServiceImpl.class);

    private final AirlineCompanyRepository airlineCompanyRepository;

    @Autowired
    public AirlineCompanyServiceImpl(AirlineCompanyRepository airlineCompanyRepository) {
        this.airlineCompanyRepository = airlineCompanyRepository;
    }

    @Override
    public List<AirlineCompany> getAllAirlineCompanyList() {

        return airlineCompanyRepository.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<AirlineCompany> getAirlineCompanyListByName(String name) {
        if (StringUtils.isEmpty(name)) {
            throw new FlightBookingException(ErrorMessages.NAME_CAN_NOT_BE_NULL);
        }
        LOGGER.debug("name :{}", name);
        return airlineCompanyRepository.getAirlineCompanyByNameContainingOrderByNameAsc(name);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public AirlineCompanyDto saveAirlineCompany(AirlineCompanyDto airlineCompanyDto) {

        AirlineCompany airlineCompany = AirlineCompanyMapper.INSTANCE.convertToAirlineCompany(airlineCompanyDto);

        Assert.notNull(airlineCompanyDto.getName(), ErrorMessages.NAME_CAN_NOT_BE_NULL);

        airlineCompanyRepository.save(airlineCompany);


        return AirlineCompanyMapper.INSTANCE.convertToAirlineCompanyDto(airlineCompany);
    }
}
