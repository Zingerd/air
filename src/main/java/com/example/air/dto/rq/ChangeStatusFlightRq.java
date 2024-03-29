package com.example.air.dto.rq;

import lombok.Data;

@Data
public class ChangeStatusFlightRq {
    private Long flightId;
    private String statusFlight;
}
