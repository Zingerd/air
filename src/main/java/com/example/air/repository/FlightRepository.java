package com.example.air.repository;



import com.example.air.entity.AirCompany;
import com.example.air.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight,Long> {
    List<Flight> findByAirCompanyAndFlightStatus(AirCompany airCompany, String status);
    List<Flight> findAllByFlightStatusAndStartedAtBefore(String status, Date date);

    List<Flight> findAllByFlightStatus(String flightStatus);
}
