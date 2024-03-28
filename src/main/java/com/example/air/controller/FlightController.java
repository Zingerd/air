package com.example.air.controller;


import com.example.air.dto.rq.FlightDtoRQ;
import com.example.air.entity.Flight;
import com.example.air.service.FlightServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    private final FlightServiceImpl flightService;

    public FlightController(FlightServiceImpl flightService) {
        this.flightService = flightService;
    }

    // TODO:
    //  Endpoint to find all Flights in ACTIVE status and started more than 24 hours ago
    @GetMapping("/active")
    public List<FlightDtoRQ> findActiveFlights() {
        return flightService.findActiveFlights();
    }

    // TODO:
    //  Endpoint to add new Flight (set status to PENDING)
    @PostMapping("/add")
    public FlightDtoRQ addFlight(@RequestBody Flight flight) {
        return flightService.addFlight(flight);
    }

    // TODO:
    //  Endpoint to change Flight status:
    //  if status to change is DELAYED – set delay started at
    //  if status to change is ACTIVE – set started at
    //  if status to change is COMPLETED – set ended at
    @PutMapping("/{flightId}/change-status/{status}")
    public FlightDtoRQ changeFlightStatus(@PathVariable Long flightId, @PathVariable String status) throws Exception {
        return flightService.changeFlightStatus(flightId, status);
    }

    // TODO:
    //  Endpoint to find all Flights in COMPLETED status and difference between
    //  started and ended time is bigger than the estimated flight time
    @GetMapping("/completed-flights")
    public List<FlightDtoRQ> getCompletedFlightsWithTimeDifference() {
        return flightService.getCompletedFlightsWithTimeDifference();
    }


}

