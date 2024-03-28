package com.example.air.controller;

import com.example.air.dto.rq.AirCompanyDtoRQ;
import com.example.air.entity.AirCompany;
import com.example.air.service.AirCompaniesServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/air/companies")
public class AirCompanyController {

    private final AirCompaniesServiceImpl airCompanyService;

    AirCompanyController(AirCompaniesServiceImpl airCompaniesService) {
        this.airCompanyService = airCompaniesService;
    }

    //TODO:
    // Simple CRUD operations for an Air company entity;
    @GetMapping
    public List<AirCompanyDtoRQ> getAllAirCompanies() {
        return airCompanyService.getAllAirCompanies();
    }

    @GetMapping("/{id}")
    public AirCompanyDtoRQ getAirCompanyById(@PathVariable Long id) {
        return airCompanyService.getAirCompanyById(id);
    }

    @PostMapping
    public AirCompanyDtoRQ createAirCompany(@RequestBody AirCompany airCompany) {
        return airCompanyService.createAirCompany(airCompany);
    }

    @PutMapping("/{id}")
    public AirCompanyDtoRQ updateAirCompany(@PathVariable Long id, @RequestBody AirCompany updatedAirCompany) {
        return airCompanyService.updateAirCompany(id, updatedAirCompany);
    }

    @DeleteMapping("/{id}")
    public void deleteAirCompany(@PathVariable Long id) {
        airCompanyService.deleteAirCompany(id);
    }
}
