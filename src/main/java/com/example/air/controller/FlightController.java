package com.example.air.controller;


import com.example.air.dto.rq.ChangeStatusFlightRq;
import com.example.air.dto.rq.FlightDtoRQ;
import com.example.air.service.impl.FlightServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.air.tools.ValidatorHandler.validChangeStatusFlightRq;
import static com.example.air.tools.ValidatorHandler.validFlightDtoRq;

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
    public FlightDtoRQ addFlight(@RequestBody FlightDtoRQ flight) {
        validFlightDtoRq(flight);
        return flightService.addFlight(flight);
    }

    // TODO:
    //  Endpoint to change Flight status:
    //  if status to change is DELAYED – set delay started at
    //  if status to change is ACTIVE – set started at
    //  if status to change is COMPLETED – set ended at
    @PostMapping("/change-status")
    public FlightDtoRQ changeFlightStatus(@RequestBody ChangeStatusFlightRq rq) throws Exception {
        validChangeStatusFlightRq(rq);
        return flightService.changeFlightStatus(rq);
    }

    // TODO:
    //  Endpoint to find all Flights in COMPLETED status and difference between
    //  started and ended time is bigger than the estimated flight time
    @GetMapping("/completed-flights")
    public List<FlightDtoRQ> getCompletedFlightsWithTimeDifference() {
        return flightService.getCompletedFlightsWithTimeDifference();
    }


}

