package com.example.air.dto.rq;

import com.example.air.tools.StatusFlightEnum;
import lombok.Data;

@Data
public class ChangeStatusFlightRq {
    private Long flightId;
    private StatusFlightEnum statusFlight;
}
