package com.finartz.flightbooking.service.mapper;

import com.finartz.flightbooking.domain.dto.AirlineCompanyDto;
import com.finartz.flightbooking.domain.entity.AirlineCompany;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AirlineCompanyMapper {
    AirlineCompanyMapper INSTANCE = Mappers.getMapper(AirlineCompanyMapper.class);

    AirlineCompany convertToAirlineCompany(AirlineCompanyDto dto);

    AirlineCompanyDto convertToAirlineCompanyDto(AirlineCompany entity);
}
