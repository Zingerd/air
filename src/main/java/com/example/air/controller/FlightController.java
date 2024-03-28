package com.example.air.controller;


import com.example.air.entity.Flight;
import com.example.air.service.FlightServiceImpl;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Flight>> findActiveFlights() {
        List<Flight> activeFlights = flightService.findActiveFlights();
        return ResponseEntity.ok(activeFlights);
    }

    // TODO:
    //  Endpoint to add new Flight (set status to PENDING)
    @PostMapping("/add")
    public ResponseEntity<Flight> addFlight(@RequestBody Flight flight) {
        Flight savedFlight = flightService.addFlight(flight);
        return ResponseEntity.ok(savedFlight);
    }

    // TODO:
    //  Endpoint to change Flight status:
    //  if status to change is DELAYED – set delay started at
    //  if status to change is ACTIVE – set started at
    //  if status to change is COMPLETED – set ended at
    @PutMapping("/{flightId}/change-status/{status}")
    public ResponseEntity<Flight> changeFlightStatus(@PathVariable Long flightId, @PathVariable String status) {
        return flightService.changeFlightStatus(flightId, status);
    }

    // TODO:
    //  Endpoint to find all Flights in COMPLETED status and difference between
    //  started and ended time is bigger than the estimated flight time
    @GetMapping("/completed-flights")
    public ResponseEntity<List<Flight>> getCompletedFlightsWithTimeDifference() {
        return ResponseEntity.ok(flightService.getCompletedFlightsWithTimeDifference());
    }


}

