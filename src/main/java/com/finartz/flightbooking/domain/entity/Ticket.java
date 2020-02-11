package com.finartz.flightbooking.domain.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Entity
public class Ticket extends BaseEntity {

    private String ticketNo;
    private BigDecimal totalPrice;
    private List<FlightBooking> flightBookingList;
    private List<Passenger> passengerList;
    private TicketStatus status;


    public enum TicketStatus {
        SUCCESS, CANCELED
    }


    public String getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
    }

    @Column(precision = 18, scale = 2)
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @OneToMany(fetch = FetchType.LAZY)
    public List<FlightBooking> getFlightBookingList() {
        return flightBookingList;
    }

    public void setFlightBookingList(List<FlightBooking> flightBookingList) {
        this.flightBookingList = flightBookingList;
    }

    @OneToMany(fetch = FetchType.LAZY)
    public List<Passenger> getPassengerList() {
        return passengerList;
    }

    public void setPassengerList(List<Passenger> passengerList) {
        this.passengerList = passengerList;
    }


    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(ticketNo, ticket.ticketNo) &&
                Objects.equals(totalPrice, ticket.totalPrice) &&
                Objects.equals(flightBookingList, ticket.flightBookingList) &&
                Objects.equals(passengerList, ticket.passengerList) &&
                status == ticket.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketNo, totalPrice, flightBookingList, passengerList, status);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketNo='" + ticketNo + '\'' +
                ", totalPrice=" + totalPrice +
                ", flightBookingList=" + flightBookingList +
                ", passengerList=" + passengerList +
                ", status=" + status +
                '}';
    }
}
