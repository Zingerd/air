package com.example.air.dto.rq;

import lombok.Data;

@Data
public class MoveAirplaneToCompanyDtoRq {
    private Long airplaneId;
    private Long companyId;
}
