package com.example.air.service;

import com.example.air.dto.rq.ChangeStatusFlightRq;
import com.example.air.dto.rq.FlightDtoRQ;

import java.util.List;

public interface FlightService {
    List<FlightDtoRQ> findActiveFlights();
    FlightDtoRQ addFlight(FlightDtoRQ flight);
    FlightDtoRQ changeFlightStatus(ChangeStatusFlightRq rq) throws Exception;
    List<FlightDtoRQ> getCompletedFlightsWithTimeDifference();
}
