package com.finartz.flightbooking.domain.entity;

import javax.persistence.*;

@Entity
public class FlightRoute extends BaseEntity {


    private Airport departure;
    private Airport arrival;

    public FlightRoute() {
    }

    public FlightRoute(Airport departure, Airport arrival) {
        super();
        this.departure = departure;
        this.arrival = arrival;
    }

    @ManyToOne
    @JoinColumn(name = "departure_airport_id", nullable = false)
    public Airport getDeparture() {
        return departure;
    }

    public void setDeparture(Airport departure) {
        this.departure = departure;
    }

    @ManyToOne
    @JoinColumn(name = "arrival_airport_id", nullable = false)
    public Airport getArrival() {
        return arrival;
    }

    public void setArrival(Airport arrival) {
        this.arrival = arrival;
    }
}
