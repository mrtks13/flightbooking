package com.finartz.flightbooking.repository;

import com.finartz.flightbooking.domain.entity.AirlineCompany;
import com.finartz.flightbooking.domain.entity.FlightBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightBookingRepository extends JpaRepository<FlightBooking,Long>  {

}
