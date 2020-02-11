package com.finartz.flightbooking.domain.dto;

import com.finartz.flightbooking.domain.entity.Flight;
import com.finartz.flightbooking.domain.entity.FlightBooking;

import java.math.BigDecimal;

public class FlightBookingResultDto {



    private String flightCode;
    private String airPortName;
    private String airlineCompanyName;
    private Integer countOfSeat;
    private String bookingNo;
    private BigDecimal totalPrice;
    private String flightBookingTypeName;
    private String flightBookingStatusName;

    public String getFlightCode() {
        return flightCode;
    }

    public void setFlightCode(String flightCode) {
        this.flightCode = flightCode;
    }

    public String getAirPortName() {
        return airPortName;
    }

    public void setAirPortName(String airPortName) {
        this.airPortName = airPortName;
    }

    public String getAirlineCompanyName() {
        return airlineCompanyName;
    }

    public void setAirlineCompanyName(String airlineCompanyName) {
        this.airlineCompanyName = airlineCompanyName;
    }

    public Integer getCountOfSeat() {
        return countOfSeat;
    }

    public void setCountOfSeat(Integer countOfSeat) {
        this.countOfSeat = countOfSeat;
    }

    public String getBookingNo() {
        return bookingNo;
    }

    public void setBookingNo(String bookingNo) {
        this.bookingNo = bookingNo;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getFlightBookingTypeName() {
        return flightBookingTypeName;
    }

    public void setFlightBookingTypeName(String flightBookingTypeName) {
        this.flightBookingTypeName = flightBookingTypeName;
    }

    public String getFlightBookingStatusName() {
        return flightBookingStatusName;
    }

    public void setFlightBookingStatusName(String flightBookingStatusName) {
        this.flightBookingStatusName = flightBookingStatusName;
    }
}
