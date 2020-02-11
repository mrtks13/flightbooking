package com.finartz.flightbooking.domain.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

public class TicketBookingRequestDto implements Serializable {

    @NotEmpty
    private List<PassengerDto> passengerDtoList;
    @NotNull
    private Long goingFlightId;
    private Long returnFlightId;
    private String email;

    public List<PassengerDto> getPassengerDtoList() {
        return passengerDtoList;
    }

    public void setPassengerDtoList(List<PassengerDto> passengerDtoList) {
        this.passengerDtoList = passengerDtoList;
    }


    public Long getGoingFlightId() {
        return goingFlightId;
    }

    public void setGoingFlightId(Long goingFlightId) {
        this.goingFlightId = goingFlightId;
    }

    public Long getReturnFlightId() {
        return returnFlightId;
    }

    public void setReturnFlightId(Long returnFlightId) {
        this.returnFlightId = returnFlightId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "TicketBookingRequestDto{" +
                "passengerDtoList=" + passengerDtoList +
                ", goingFlightId=" + goingFlightId +
                ", returnFlightId=" + returnFlightId +
                ", email='" + email + '\'' +
                '}';
    }
}
