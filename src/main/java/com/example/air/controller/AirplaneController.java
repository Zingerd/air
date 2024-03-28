package com.example.air.controller;


import com.example.air.dto.rq.AirplaneDtoRQ;
import com.example.air.entity.Airplane;
import com.example.air.service.AirplaneServiceImpl;
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
    public AirplaneDtoRQ addAirplane(@RequestBody Airplane airplane) {

        return airplaneService.addAirplane(airplane);
    }

    // TODO:
    //  Endpoint to move airplanes between companies (simple endpoint to reassign airplane
    //  to another company)
    @PutMapping("/{airplaneId}/move/{companyId}")
    public String moveAirplaneToCompany(@PathVariable Long airplaneId, @PathVariable Long companyId) {
        return airplaneService.moveAirplaneToCompany(airplaneId, companyId);
    }
}
