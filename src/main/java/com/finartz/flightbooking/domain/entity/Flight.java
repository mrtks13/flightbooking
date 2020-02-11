package com.finartz.flightbooking.domain.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Entity
public class Flight extends BaseEntity {

    private String flightCode;
    private FlightRoute flightRoute;
    private AirlineCompany airlineCompany;
    private Integer numberOfTotalSeat;
    private Integer numberOfBookingSeat;
    private Integer numberOfAvailableSeat;
    private BigDecimal ticketPrice;
    private BigDecimal lastTicketPrice;
    private LocalDateTime departureDate;
    private Set<FlightBooking> flightBookings;

    public Flight() {

    }

    public Flight(FlightRoute flightRoute, AirlineCompany airlineCompany, Integer quata, LocalDateTime departureDate) {
        this.flightRoute = flightRoute;
        this.airlineCompany = airlineCompany;
        this.numberOfTotalSeat = quata;
        this.departureDate = departureDate;
    }


    @Column(nullable = false)
    public String getFlightCode() {
        return flightCode;
    }

    public void setFlightCode(String flightCode) {
        this.flightCode = flightCode;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_route_id")
    public FlightRoute getFlightRoute() {
        return flightRoute;
    }

    public void setFlightRoute(FlightRoute flightRoute) {
        this.flightRoute = flightRoute;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airline_company_id")
    public AirlineCompany getAirlineCompany() {
        return airlineCompany;
    }

    public void setAirlineCompany(AirlineCompany airlineCompany) {
        this.airlineCompany = airlineCompany;
    }

    public Integer getNumberOfTotalSeat() {
        return numberOfTotalSeat;
    }

    public void setNumberOfTotalSeat(Integer numberOfTotalSeat) {
        this.numberOfTotalSeat = numberOfTotalSeat;
    }

    @Column(precision = 18, scale = 2, nullable = false)
    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    @Column(precision = 18, scale = 2)
    public BigDecimal getLastTicketPrice() {
        if (lastTicketPrice == null) {
            return ticketPrice;
        }
        return lastTicketPrice;
    }

    public void setLastTicketPrice(BigDecimal lastTicketPrice) {
        this.lastTicketPrice = lastTicketPrice;
    }


    public LocalDateTime getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDateTime departureDate) {
        this.departureDate = departureDate;
    }


    @ManyToMany(fetch = FetchType.LAZY)
    public Set<FlightBooking> getFlightBookings() {
        return flightBookings;
    }

    public void setFlightBookings(Set<FlightBooking> flightBookings) {
        this.flightBookings = flightBookings;
    }


    public Integer getNumberOfBookingSeat() {
        if (this.numberOfBookingSeat == null) {
            return 0;
        }
        return numberOfBookingSeat;
    }

    public void setNumberOfBookingSeat(Integer numberOfBookingSeat) {
        this.numberOfBookingSeat = numberOfBookingSeat;
    }

    @Column(name = "numberOfAvailableSeat", nullable = false)
    public Integer getNumberOfAvailableSeat() {
        numberOfAvailableSeat = getNumberOfTotalSeat() - getNumberOfBookingSeat();
        return numberOfAvailableSeat;
    }

    public void setNumberOfAvailableSeat(Integer numberOfAvailableSeat) {
        this.numberOfAvailableSeat = numberOfAvailableSeat;
    }
}
