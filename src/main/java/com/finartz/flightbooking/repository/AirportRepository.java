package com.finartz.flightbooking.repository;

import com.finartz.flightbooking.domain.entity.AirlineCompany;
import com.finartz.flightbooking.domain.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {

    @Query("select p from Airport as  p where upper(p.name) like concat('%',upper(:name),'%' ) order by  p.name asc")
    List<Airport> findAirportsByName(@Param("name") String name);

    List<Airport> findAirportsByIataCodeEquals(String iataCode);
}
