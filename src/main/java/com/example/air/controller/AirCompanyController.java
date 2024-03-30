package com.example.air.controller;

import com.example.air.dto.rq.AirCompanyDtoRQ;
import com.example.air.service.impl.AirCompaniesServiceImpl;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.example.air.tools.ValidatorHandler.validatorAirCompanyDtoRq;

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
    public AirCompanyDtoRQ createAirCompany(@RequestBody AirCompanyDtoRQ airCompany) {
        validatorAirCompanyDtoRq(airCompany);
        return airCompanyService.createAirCompany(airCompany);
    }

    @PutMapping("/{id}")
    public AirCompanyDtoRQ updateAirCompany(@PathVariable Long id, @RequestBody AirCompanyDtoRQ updatedAirCompany) {
        validatorAirCompanyDtoRq(updatedAirCompany);
        return airCompanyService.updateAirCompany(id, updatedAirCompany);
    }

    @DeleteMapping("/{id}")
    public String deleteAirCompany(@PathVariable Long id) {
        return airCompanyService.deleteAirCompany(id);
    }
}
