package com.example.air.dto.rq;

import lombok.Data;

import java.util.Date;

@Data
public class AirplaneDtoRQ {
    private Long id;
    private String name;
    private String factorySerialNumber;
    private Long airCompanyId;
    private Integer numberOfFlights;
    private Double flightDistance;
    private Double fuelCapacity;
    private String type;
    private Date createdAt;
}
