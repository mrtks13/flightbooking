package com.finartz.flightbooking.service.mapper;

import com.finartz.flightbooking.domain.dto.TicketBookingResponseDto;
import com.finartz.flightbooking.domain.entity.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TicketMapper {

    TicketMapper INSTANCE = Mappers.getMapper(TicketMapper.class);

    TicketBookingResponseDto convertToDto(Ticket ticket);
}
