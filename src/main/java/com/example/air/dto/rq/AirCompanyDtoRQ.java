package com.example.air.dto.rq;

import lombok.Data;

import java.util.Date;

@Data
public class AirCompanyDtoRQ {
    private Long id;
    private String name;
    private String companyType;
    private Date foundedAt;
}
