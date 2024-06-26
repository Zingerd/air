package com.example.air.service.impl;


import com.example.air.dto.rq.ChangeStatusFlightRq;
import com.example.air.dto.rq.FlightDtoRQ;
import com.example.air.entity.Flight;
import com.example.air.repository.FlightRepository;
import com.example.air.service.FlightService;
import javassist.NotFoundException;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.air.tools.ConvertorObjects.convertToDtoFlight;
import static com.example.air.tools.ConvertorObjects.convertToDtoFlightList;
import static com.example.air.tools.ConvertorObjects.convertToEntytyFlight;
import static com.example.air.tools.ExceptionHandler.exceptionHandlerRequest;
import static com.example.air.tools.StatusFlightEnum.ACTIVE;
import static com.example.air.tools.StatusFlightEnum.COMPLETED;
import static com.example.air.tools.StatusFlightEnum.DELAYED;


@Service
@Log4j2
public class FlightServiceImpl implements FlightService {
    private final FlightRepository flightRepository;

    private final ModelMapper modelMapper;

    public FlightServiceImpl(FlightRepository flightRepository, ModelMapper modelMapper) {
        this.flightRepository = flightRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<FlightDtoRQ> findActiveFlights() {
        log.info("findActiveFlights");
        List<Flight> flights = flightRepository.findAllByFlightStatusAndStartedAtBefore(ACTIVE.name(),
                new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000)).orElseThrow();

        return convertToDtoFlightList(flights, modelMapper);
    }

    @Override
    public FlightDtoRQ addFlight(FlightDtoRQ flight) {
        log.info("addFlight");
        return convertToDtoFlight(flightRepository.save(convertToEntytyFlight(flight, modelMapper)), modelMapper);
    }

    @Override
    public FlightDtoRQ changeFlightStatus(ChangeStatusFlightRq rq) {
        try {
            Flight flight = flightRepository.findById(rq.getFlightId())
                    .orElseThrow(() -> new NotFoundException("Flight not found"));

            switch (rq.getStatusFlight()) {
                case DELAYED:
                    flight.setFlightStatus(DELAYED.name());
                    flight.setDelayStartedAt(new Date());
                    break;
                case ACTIVE:
                    flight.setFlightStatus(ACTIVE.name());
                    flight.setStartedAt(new Date());
                    break;
                case COMPLETED:
                    flight.setFlightStatus(COMPLETED.name());
                    flight.setEndedAt(new Date());
                    break;
                default:
                    throw new NotFoundException("Incorrect Flight status");
            }
            flightRepository.save(flight);
            return convertToDtoFlight(flight, modelMapper);
        } catch (Exception e) {
            throw exceptionHandlerRequest(e);
        }
    }

    @Override
    public List<FlightDtoRQ> getCompletedFlightsWithTimeDifference() {
        try {
            log.info("getCompletedFlightsWithTimeDifference");
            List<Flight> completedFlights = flightRepository.findAllByFlightStatus(COMPLETED.name())
                    .orElseThrow();

            List<Flight> resultFlights = new ArrayList<>();

            for (Flight flight : completedFlights) {
                long estimatedFlightTimeMillis = convertTimeToMillis(flight.getEstimatedFlightTime());
                long differenceMillis = flight.getEndedAt().getTime() - flight.getStartedAt().getTime();

                if (differenceMillis > estimatedFlightTimeMillis) {
                    resultFlights.add(flight);
                }
            }
            return convertToDtoFlightList(resultFlights, modelMapper);
        } catch (Exception e) {
            throw exceptionHandlerRequest(e);
        }
    }


    private long convertTimeToMillis(String time) {
        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        int seconds = Integer.parseInt(parts[2]);

        return (hours * 3600L + minutes * 60L + seconds) * 1000;
    }
}
