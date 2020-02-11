package com.finartz.flightbooking.service.mapper;

import com.finartz.flightbooking.domain.dto.TicketBookingResponseDto;
import com.finartz.flightbooking.domain.entity.Ticket;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TicketMapperTest {

    @Test
    public void shouldMapTicketToDto() {
        //given
        Ticket ticket = new Ticket();
        ticket.setTotalPrice(new BigDecimal(250));
        ticket.setTicketNo("TF-25");

        //when
        TicketBookingResponseDto ticketBookingResponseDto = TicketMapper.INSTANCE.convertToDto(ticket);

        //then
        assertNotNull(ticketBookingResponseDto);

    }

}