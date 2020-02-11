package com.finartz.flightbooking.repository;

import com.finartz.flightbooking.domain.dto.FlightSearchDto;
import com.finartz.flightbooking.domain.entity.AirlineCompany;
import com.finartz.flightbooking.domain.entity.Airport;
import com.finartz.flightbooking.domain.entity.Flight;
import com.finartz.flightbooking.domain.entity.FlightRoute;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;


public class FlightRepositoryCustomImpl implements FlightRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;


    @Override
    public List<Flight> searchFlights(FlightSearchDto flightSearchDto) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Flight> cq = cb.createQuery(Flight.class);

        Root<Flight> f = cq.from(Flight.class);
        Join<Flight, FlightRoute> departureRoute = f.join("flightRoute", JoinType.INNER);
        Join<FlightRoute, Airport> departureAirport = departureRoute.join("departure", JoinType.INNER);
        Join<FlightRoute, Airport> arrivalAirport = departureRoute.join("arrival", JoinType.INNER);

        Join<Flight, AirlineCompany> airlineCompany = f.join("airlineCompany", JoinType.INNER);

        List<Predicate> predicates = new ArrayList<>();


        if (flightSearchDto.getAirlineCompanyId() != null) {
            predicates.add(cb.equal(airlineCompany.get("id"), flightSearchDto.getAirlineCompanyId()));
        }

        Predicate predicateForGoingDepartureDate = cb.equal(f.get("departureDate"), flightSearchDto.getDepartureDate());
        Predicate predicateForGoingDepartureAirport = cb.equal(departureAirport.get("id"), flightSearchDto.getDepartureAirportId());
        Predicate predicateForGoingArrivalAirport = cb.equal(arrivalAirport.get("id"), flightSearchDto.getArrivalAirportId());
        Predicate predicateForGoingNumberOfSeat = cb.ge(f.get("numberOfAvailableSeat"), flightSearchDto.getNumberOfSeat());

        Predicate predicateForGoing = cb.and(predicateForGoingDepartureDate, predicateForGoingDepartureAirport, predicateForGoingArrivalAirport, predicateForGoingNumberOfSeat);

        if (flightSearchDto.getOnlyGoing()) {
            predicates.add(predicateForGoing);

        } else {
            Join<Flight, FlightRoute> returnRoute = f.join("flightRoute", JoinType.INNER);
            Join<FlightRoute, Airport> returnDepartureAirport = returnRoute.join("departure", JoinType.INNER);
            Join<FlightRoute, Airport> returnArrivalAirport = returnRoute.join("arrival", JoinType.INNER);

            Predicate predicateForReturnDepartureDate = cb.equal(f.get("departureDate"), flightSearchDto.getReturnDate());
            Predicate predicateForReturnDepartureAirport = cb.equal(returnDepartureAirport.get("id"), flightSearchDto.getArrivalAirportId());
            Predicate predicateForReturnArrivalAirport = cb.equal(returnArrivalAirport.get("id"), flightSearchDto.getDepartureAirportId());
            Predicate predicateForReturnNumberOfSeat = cb.ge(f.get("numberOfAvailableSeat"), flightSearchDto.getNumberOfSeat());

            Predicate predicateForReturn = cb.and(predicateForReturnDepartureDate, predicateForReturnDepartureAirport, predicateForReturnArrivalAirport, predicateForReturnNumberOfSeat);

            Predicate finalPredicate = cb.or(predicateForGoing, predicateForReturn);
            predicates.add(finalPredicate);
        }


        cq.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(cq).getResultList();

    }


}
