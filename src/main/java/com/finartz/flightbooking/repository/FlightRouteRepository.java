package com.finartz.flightbooking.repository;

import com.finartz.flightbooking.domain.entity.AirlineCompany;
import com.finartz.flightbooking.domain.entity.FlightRoute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRouteRepository extends JpaRepository<FlightRoute,Long>  {

    List<FlightRoute> getAllByArrival_NameAndDeparture_NameOrderById(String arrivalName,String departureName);

    List<FlightRoute> findAllByArrivalAndDeparture_Id(Long arrivalId,Long departureId);
}
