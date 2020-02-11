package com.finartz.flightbooking.repository;

import com.finartz.flightbooking.domain.entity.Flight;
import com.finartz.flightbooking.domain.entity.FlightRoute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long>,FlightRepositoryCustom {


}
