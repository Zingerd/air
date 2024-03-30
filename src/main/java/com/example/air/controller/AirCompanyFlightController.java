package com.example.air.controller;

import com.example.air.dto.rq.FlightDtoRQ;
import com.example.air.service.impl.AirCompaniesServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("/{companyName}/flights/{status}")
    public List<FlightDtoRQ> getFlightsByAirCompanyAndStatus(@PathVariable String companyName, @PathVariable String status) {
        return airCompaniesService.getFlightsByAirCompanyAndStatus(companyName, status);
    }

}
