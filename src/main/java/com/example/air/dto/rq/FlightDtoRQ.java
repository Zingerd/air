package com.example.air.dto.rq;

import lombok.Data;

import java.util.Date;

@Data
public class FlightDtoRQ {

    private Long id;
    private String flightStatus;
    private Long airCompanyId;
    private Long airplaneId;
    private String departureCountry;
    private String destinationCountry;
    private Float distance;
    private String estimatedFlightTime;
    private Date startedAt;
    private Date endedAt;
    private Date delayStartedAt;
    private Date createdAt;
}
