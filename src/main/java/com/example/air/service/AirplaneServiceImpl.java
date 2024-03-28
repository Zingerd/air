package com.example.air.service;


import com.example.air.entity.AirCompany;
import com.example.air.entity.Airplane;
import com.example.air.repository.AirCompanyRepository;
import com.example.air.repository.AirplaneRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AirplaneServiceImpl {
    private final AirplaneRepository airplaneRepository;
    private final AirCompanyRepository airCompanyRepository;

    public AirplaneServiceImpl(AirplaneRepository airplaneRepository, AirCompanyRepository airCompanyRepository) {
        this.airplaneRepository = airplaneRepository;
        this.airCompanyRepository = airCompanyRepository;
    }

    public Airplane addAirplane(Airplane airplane) {
        return airplaneRepository.save(airplane);
    }

    public ResponseEntity<String> moveAirplaneToCompany(Long airplaneId, Long companyId) {
        Optional<Airplane> airplaneOptional = airplaneRepository.findById(airplaneId);
        Optional<AirCompany> airCompanyOptional = airCompanyRepository.findById(companyId);

        if (airplaneOptional.isPresent() && airCompanyOptional.isPresent()) {
            Airplane airplane = airplaneOptional.get();
            AirCompany newAirCompany = airCompanyOptional.get();

            airplane.setAirCompany(newAirCompany);
            airplaneRepository.save(airplane);

            return ResponseEntity.ok("Airplane moved to company: " + airCompanyOptional.get().getName() +
                    ", with ID:" + companyId);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
