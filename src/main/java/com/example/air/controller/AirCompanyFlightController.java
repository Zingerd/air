package com.example.air.controller;

import com.example.air.dto.rq.FlightDtoRQ;
import com.example.air.service.AirCompaniesServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
public class AirCompanyFlightController {

    AirCompaniesServiceImpl airCompaniesService;

    public AirCompanyFlightController(AirCompaniesServiceImpl airCompaniesService) {
        this.airCompaniesService = airCompaniesService;
    }

    // TODO:
    //    Endpoint to find all Air Company Flights by status (use company name for
    //    identification of Air Company);
    @GetMapping("/{companyName}/flights")
    public List<FlightDtoRQ> getFlightsByAirCompanyAndStatus(@PathVariable String companyName, @RequestParam String status) {
        return airCompaniesService.getFlightsByAirCompanyAndStatus(companyName, status);
    }

}
