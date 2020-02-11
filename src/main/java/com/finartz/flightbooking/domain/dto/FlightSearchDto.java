package com.finartz.flightbooking.domain.dto;

import com.finartz.flightbooking.domain.entity.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class FlightSearchDto  {


    private Long departureAirportId;
    private Long arrivalAirportId;
    private LocalDateTime departureDate;
    private LocalDateTime returnDate;
    private Long airlineCompanyId;
    private Integer numberOfSeat;
    private Boolean onlyGoing;



    public FlightSearchDto(Long departureAirportId, Long arrivalAirportId, LocalDateTime departureDate, LocalDateTime returnDate, Long airlineCompanyId, Integer numberOfSeat, Boolean onlyGoing) {
        this.departureAirportId = departureAirportId;
        this.arrivalAirportId = arrivalAirportId;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.airlineCompanyId = airlineCompanyId;
        this.numberOfSeat = numberOfSeat;
        this.onlyGoing = onlyGoing;
    }

    public Long getDepartureAirportId() {
        return departureAirportId;
    }

    public void setDepartureAirportId(Long departureAirportId) {
        this.departureAirportId = departureAirportId;
    }

    public Long getArrivalAirportId() {
        return arrivalAirportId;
    }

    public void setArrivalAirportId(Long arrivalAirportId) {
        this.arrivalAirportId = arrivalAirportId;
    }

    public LocalDateTime getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDateTime departureDate) {
        this.departureDate = departureDate;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

    public Long getAirlineCompanyId() {
        return airlineCompanyId;
    }

    public void setAirlineCompanyId(Long airlineCompanyId) {
        this.airlineCompanyId = airlineCompanyId;
    }

    public Integer getNumberOfSeat() {
        return numberOfSeat;
    }

    public void setNumberOfSeat(Integer numberOfSeat) {
        this.numberOfSeat = numberOfSeat;
    }

    public Boolean getOnlyGoing() {
        return onlyGoing;
    }

    public void setOnlyGoing(Boolean onlyGoing) {
        this.onlyGoing = onlyGoing;
    }
}
