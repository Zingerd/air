package com.example.air.controller;

import com.example.air.entity.AirCompany;
import com.example.air.service.AirCompaniesServiceImpl;
import com.example.air.tools.ExceptionHandler;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<AirCompany>> getAllAirCompanies() {
        List<AirCompany> airCompanies = airCompanyService.getAllAirCompanies();
        return ResponseEntity.ok(airCompanies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AirCompany> getAirCompanyById(@PathVariable Long id) throws ExceptionHandler {
        AirCompany airCompany = airCompanyService.getAirCompanyById(id);
        if (airCompany != null) {
            return ResponseEntity.ok(airCompany);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<AirCompany> createAirCompany(@RequestBody AirCompany airCompany) {
        AirCompany createdAirCompany = airCompanyService.createAirCompany(airCompany);
        return ResponseEntity.ok(createdAirCompany);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AirCompany> updateAirCompany(@PathVariable Long id, @RequestBody AirCompany updatedAirCompany) {
        AirCompany airCompany = airCompanyService.updateAirCompany(id, updatedAirCompany);
        if (airCompany != null) {
            return ResponseEntity.ok(airCompany);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAirCompany(@PathVariable Long id) {
        airCompanyService.deleteAirCompany(id);
        return ResponseEntity.noContent().build();
    }
}
