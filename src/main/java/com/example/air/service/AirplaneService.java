package com.example.air.service;

import com.example.air.dto.rq.AirplaneDtoRQ;
import com.example.air.dto.rq.MoveAirplaneToCompanyDtoRq;

public interface AirplaneService {
    AirplaneDtoRQ addAirplane(AirplaneDtoRQ airplane);
    String moveAirplaneToCompany(MoveAirplaneToCompanyDtoRq rq);
}
