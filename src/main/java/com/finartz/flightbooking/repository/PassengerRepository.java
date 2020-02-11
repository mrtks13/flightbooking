package com.finartz.flightbooking.repository;

import com.finartz.flightbooking.domain.entity.AirlineCompany;
import com.finartz.flightbooking.domain.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger,Long>  {

}
