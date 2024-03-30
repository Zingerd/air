package com.example.air.tools;


import com.example.air.dto.rq.AirCompanyDtoRQ;
import com.example.air.dto.rq.AirplaneDtoRQ;
import com.example.air.dto.rq.ChangeStatusFlightRq;
import com.example.air.dto.rq.FlightDtoRQ;
import com.example.air.dto.rq.MoveAirplaneToCompanyDtoRq;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static com.example.air.tools.StatusFlightEnum.ACTIVE;
import static com.example.air.tools.StatusFlightEnum.COMPLETED;
import static com.example.air.tools.StatusFlightEnum.DELAYED;
import static com.example.air.tools.StatusFlightEnum.PENDING;

public class ValidatorHandler {

    private static final Set<String> STATUS_FLING = new HashSet<>(Set.of(DELAYED.name(), ACTIVE.name(), PENDING.name(), COMPLETED.name()));

    public static void validatorAirCompanyDtoRq(AirCompanyDtoRQ rq) {
        Objects.requireNonNull(rq, "AirCompanyDtoRQ must not be null");
        Objects.requireNonNull(rq.getName(), "Name of company must not be null");
        Objects.requireNonNull(rq.getCompanyType(), "CompanyType  must not be null");
        Objects.requireNonNull(rq.getFoundedAt(), "foundedAt  must not be null");
    }

    public static void validatorAirplaneDtoRq(AirplaneDtoRQ rq) {
        Objects.requireNonNull(rq, "AirplaneDtoRQ must not be null");
        Objects.requireNonNull(rq.getFactorySerialNumber(), "factorySerialNumber must not be null");
        Objects.requireNonNull(rq.getAirCompanyId(), "airCompanyId  must not be null");
        Objects.requireNonNull(rq.getNumberOfFlights(), "numberOfFlights  must not be null");
        Objects.requireNonNull(rq.getFuelCapacity(), "fuelCapacity  must not be null");
        Objects.requireNonNull(rq.getType(), "type must not be null");
        Objects.requireNonNull(rq.getCreatedAt(), "createdAt  must not be null");
    }

    public static void validMoveAirplaneToCompanyDtoRq(MoveAirplaneToCompanyDtoRq rq) {
        Objects.requireNonNull(rq, "MoveAirplaneToCompanyDtoRq must not be null");
        Objects.requireNonNull(rq.getAirplaneId(), "airplaneId must not be null");
        Objects.requireNonNull(rq.getCompanyId(), "companyId must not be null");
    }

    public static void validFlightDtoRq(FlightDtoRQ rq) {
        Objects.requireNonNull(rq, "FlightDtoRQ must not be null");
        if (!PENDING.equals(rq.getFlightStatus())) {
            throw new IllegalArgumentException("Invalid value for flightStatus: " + rq.getFlightStatus() +
                    ", please use this value: PENDING");
        }
        Objects.requireNonNull(rq.getAirCompanyId(), "airCompanyId must not be null");
        Objects.requireNonNull(rq.getAirplaneId(), "airplaneId must not be null");
        Objects.requireNonNull(rq.getDepartureCountry(), "departureCountry must not be null");
        Objects.requireNonNull(rq.getDestinationCountry(), "destinationCountry must not be null");
        Objects.requireNonNull(rq.getDistance(), "distance must not be null");
        Objects.requireNonNull(rq.getEstimatedFlightTime(), "estimatedFlightTime must not be null");
        Objects.requireNonNull(rq.getStartedAt(), "startedAt must not be null");
        Objects.requireNonNull(rq.getEndedAt(), "endedAt must not be null");
        Objects.requireNonNull(rq.getStartedAt(), "startedAt must not be null");
        Objects.requireNonNull(rq.getCreatedAt(), "createdAt must not be null");
    }

    public static void validChangeStatusFlightRq(ChangeStatusFlightRq rq) {

        Objects.requireNonNull(rq, "ChangeStatusFlightRq must not be null");
        Objects.requireNonNull(rq.getFlightId(), "flightId must not be null");
        Objects.requireNonNull(rq.getStatusFlight(), "statusFlight must not be null");
        if (!STATUS_FLING.contains(rq.getStatusFlight().getStatus())) {
            throw new IllegalArgumentException("Invalid value for statusFlight, " +
                    "please use these status values [DELAYED, ACTIVE, PENDING, COMPLETED]");
        }
    }

}
