package com.finartz.flightbooking.domain.entity;

import javax.persistence.Entity;

@Entity
public class Passenger extends BaseEntity {

    private String name;
    private String lastName;
    private String email;
    private String phoneNumber;


    public Passenger(){
        super();
    }

    public Passenger(String name, String lastName, String email, String phoneNumber) {
        super();
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
