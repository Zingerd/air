package com.example.air.dto.rq;

import com.example.air.tools.StatusFlightEnum;
import lombok.Data;

import java.util.Date;

@Data
public class FlightDtoRQ {

    private Long id;
    private StatusFlightEnum flightStatus;
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
