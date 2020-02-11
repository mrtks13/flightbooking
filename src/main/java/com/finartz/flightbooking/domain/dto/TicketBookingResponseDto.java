package com.finartz.flightbooking.domain.dto;

import com.finartz.flightbooking.domain.entity.FlightBooking;

import java.math.BigDecimal;
import java.util.List;

public class TicketBookingResponseDto {

    public TicketBookingResponseDto() {
        super();
    }

    public TicketBookingResponseDto(String ticketNo, BigDecimal totalPrice, List<FlightBookingResultDto> flightBookingList, String status) {
        this.ticketNo = ticketNo;
        this.totalPrice = totalPrice;
        this.flightBookingList = flightBookingList;
        this.status = status;
    }

    private String ticketNo;
    private Long Id;
    private BigDecimal totalPrice;
    private List<FlightBookingResultDto> flightBookingList;
    private String status;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<FlightBookingResultDto> getFlightBookingList() {
        return flightBookingList;
    }

    public void setFlightBookingList(List<FlightBookingResultDto> flightBookingList) {
        this.flightBookingList = flightBookingList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
