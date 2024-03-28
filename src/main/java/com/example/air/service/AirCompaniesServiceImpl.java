package com.example.air.service;


import com.example.air.entity.AirCompany;
import com.example.air.entity.Flight;
import com.example.air.repository.AirCompanyRepository;
import com.example.air.repository.FlightRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirCompaniesServiceImpl {
    private final AirCompanyRepository airCompanyRepository;
    private final FlightRepository flightRepository;

    public AirCompaniesServiceImpl(AirCompanyRepository airCompanyRepository, FlightRepository flightRepository) {
        this.airCompanyRepository = airCompanyRepository;
        this.flightRepository = flightRepository;
    }

    public List<AirCompany> getAllAirCompanies() {
        return airCompanyRepository.findAll();
    }

    public AirCompany getAirCompanyById(Long id) {
        return airCompanyRepository.findById(id).orElse(null);
    }

    public AirCompany createAirCompany(AirCompany airCompany) {
        return airCompanyRepository.save(airCompany);
    }

    public AirCompany updateAirCompany(Long id, AirCompany updatedAirCompany) {
        AirCompany existingAirCompany = airCompanyRepository.findById(id).orElse(null);
        if (existingAirCompany != null) {
            existingAirCompany.setName(updatedAirCompany.getName());
            existingAirCompany.setCompanyType(updatedAirCompany.getCompanyType());
            existingAirCompany.setFoundedAt(updatedAirCompany.getFoundedAt());
            return airCompanyRepository.save(existingAirCompany);
        }
        return null;
    }

    public void deleteAirCompany(Long id) {
        airCompanyRepository.deleteById(id);
    }

    public ResponseEntity<List<Flight>> getFlightsByAirCompanyAndStatus(String companyName, String status) {
        Optional<AirCompany> airCompanyOptional = airCompanyRepository.findByName(companyName);

        if (airCompanyOptional.isPresent()) {
            AirCompany airCompany = airCompanyOptional.get();
            List<Flight> flights = flightRepository.findByAirCompanyAndFlightStatus(airCompany, status);
            return ResponseEntity.ok(flights);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
