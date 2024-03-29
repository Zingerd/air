package com.example.air.repository;


import com.example.air.entity.AirCompany;
import com.example.air.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    Optional<List<Flight>> findByAirCompanyAndFlightStatus(AirCompany airCompany, String status);

    Optional<List<Flight>> findAllByFlightStatusAndStartedAtBefore(String status, Date date);

    Optional<List<Flight>> findAllByFlightStatus(String flightStatus);
}
