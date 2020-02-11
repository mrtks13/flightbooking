package com.finartz.flightbooking.domain.dto;

public class AirportDto {

    private Long id;
    private String name;
    private String iataCode;
    private String countryCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIataCode() {
        return iataCode;
    }

    public void setIataCode(String iataCode) {
        this.iataCode = iataCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @Override
    public String toString() {
        return "AirportDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", iataCode='" + iataCode + '\'' +
                ", countryCode='" + countryCode + '\'' +
                '}';
    }
}
