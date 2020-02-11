package com.finartz.flightbooking.domain.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class FlightBooking extends BaseEntity {


    private Flight flight;
    private Integer countOfSeat;
    private String bookingNo;
    private BigDecimal totalPrice;
    private FlightBookingType flightBookingType;
    private FlightBookingStatus flightBookingStatus = FlightBookingStatus.CONFIRMED;

    public enum FlightBookingStatus {
        CONFIRMED, CANCELLED
    }


    public enum FlightBookingType {
        GOING, RETURN
    }


    public String getBookingNo() {
        return bookingNo;
    }

    public void setBookingNo(String bookingNo) {
        this.bookingNo = bookingNo;
    }

    @ManyToOne()
    @JoinColumn(name = "flight_id", nullable = false)
    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Integer getCountOfSeat() {
        return countOfSeat;
    }

    public void setCountOfSeat(Integer countOfSeat) {
        this.countOfSeat = countOfSeat;
    }

    @Column(precision = 18, scale = 2)
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }


    @Enumerated(EnumType.STRING)
    @Column(name = "flight_booking_type")
    public FlightBookingType getFlightBookingType() {
        return flightBookingType;
    }

    public void setFlightBookingType(FlightBookingType flightBookingType) {
        this.flightBookingType = flightBookingType;
    }


    @Enumerated(EnumType.STRING)
    @Column(name = "flight_booking_status")
    public FlightBookingStatus getFlightBookingStatus() {
        return flightBookingStatus;
    }

    public void setFlightBookingStatus(FlightBookingStatus flightBookingStatus) {
        this.flightBookingStatus = flightBookingStatus;
    }
}
