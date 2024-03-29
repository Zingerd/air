package com.example.air.controller;


import com.example.air.dto.rq.AirplaneDtoRQ;
import com.example.air.dto.rq.MoveAirplaneToCompanyDtoRq;
import com.example.air.service.impl.AirplaneServiceImpl;
import org.springframework.web.bind.annotation.*;

import static com.example.air.tools.ValidatorHandler.validMoveAirplaneToCompanyDtoRq;
import static com.example.air.tools.ValidatorHandler.validatorAirplaneDtoRq;

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
    public AirplaneDtoRQ addAirplane(@RequestBody AirplaneDtoRQ airplane) {
        validatorAirplaneDtoRq(airplane);
        return airplaneService.addAirplane(airplane);
    }

    // TODO:
    //  Endpoint to move airplanes between companies (simple endpoint to reassign airplane
    //  to another company)
    @PostMapping("/move")
    public String moveAirplaneToCompany(@RequestBody MoveAirplaneToCompanyDtoRq rq) {
        validMoveAirplaneToCompanyDtoRq(rq);
        return airplaneService.moveAirplaneToCompany(rq);
    }
}
