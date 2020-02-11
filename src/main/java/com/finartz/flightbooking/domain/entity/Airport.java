package com.finartz.flightbooking.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Airport extends BaseEntity {

    private String name;
    private String iataCode;
    private String countryCode;

    @Column(length = 100, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(length = 10, nullable = false)
    public String getIataCode() {
        return iataCode;
    }

    public void setIataCode(String iataCode) {
        this.iataCode = iataCode;
    }

    @Column(length = 10, nullable = false)
    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
