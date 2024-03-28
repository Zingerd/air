package com.example.air.controller;


import com.example.air.entity.Airplane;
import com.example.air.service.AirplaneServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/airplanes")
public class AirplaneController {

    AirplaneServiceImpl airplaneService;

    AirplaneController(AirplaneServiceImpl airplaneService) {
        this.airplaneService = airplaneService;
    }

    // TODO:
    //  Endpoint to add new Airplane
    @PostMapping("/add")
    public ResponseEntity<Airplane> addAirplane(@RequestBody Airplane airplane) {
        Airplane savedAirplane = airplaneService.addAirplane(airplane);
        return ResponseEntity.ok(savedAirplane);
    }

    // TODO:
    //  Endpoint to move airplanes between companies (simple endpoint to reassign airplane
    //  to another company)
    @PutMapping("/{airplaneId}/move/{companyId}")
    public ResponseEntity<String> moveAirplaneToCompany(@PathVariable Long airplaneId, @PathVariable Long companyId) {
        return airplaneService.moveAirplaneToCompany(airplaneId, companyId);
    }
}
