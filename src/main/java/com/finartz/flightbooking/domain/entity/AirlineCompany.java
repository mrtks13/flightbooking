package com.finartz.flightbooking.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class AirlineCompany extends BaseEntity {

    private String name;


    @Column(length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
