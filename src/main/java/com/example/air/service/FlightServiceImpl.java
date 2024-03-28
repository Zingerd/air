package com.example.air.service;


import com.example.air.dto.rq.FlightDtoRQ;
import com.example.air.entity.Flight;
import com.example.air.repository.FlightRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.air.tools.Constant.*;


@Service
public class FlightServiceImpl {
    private final FlightRepository flightRepository;

    private final ModelMapper modelMapper;

    public FlightServiceImpl(FlightRepository flightRepository, ModelMapper modelMapper) {
        this.flightRepository = flightRepository;
        this.modelMapper = modelMapper;
    }

    public List<FlightDtoRQ> findActiveFlights() {
        List<Flight> flights = flightRepository.findAllByFlightStatusAndStartedAtBefore(ACTIVE, new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000));

        return convertToDtoFlight(flights);
    }

    public FlightDtoRQ addFlight(Flight flight) {
        return convertToDtoFlight(flightRepository.save(flight));
    }

    public FlightDtoRQ changeFlightStatus(Long flightId, String status) throws Exception {
        Flight flight = flightRepository.findById(flightId).orElseThrow();

        switch (status) {
            case DELAYED:
                flight.setFlightStatus(DELAYED);
                flight.setDelayStartedAt(new Date());
                break;
            case ACTIVE:
                flight.setFlightStatus(ACTIVE);
                flight.setStartedAt(new Date());
                break;
            case COMPLETED:
                flight.setFlightStatus(COMPLETED);
                flight.setEndedAt(new Date());
                break;
            default:
                throw new Exception();
        }
        flightRepository.save(flight);
        return convertToDtoFlight(flight);
    }


    public List<FlightDtoRQ> getCompletedFlightsWithTimeDifference() {
        List<Flight> completedFlights = flightRepository.findAllByFlightStatus(COMPLETED);
        List<Flight> resultFlights = new ArrayList<>();

        for (Flight flight : completedFlights) {
            long estimatedFlightTimeMillis = convertTimeToMillis(flight.getEstimatedFlightTime());
            long differenceMillis = flight.getEndedAt().getTime() - flight.getStartedAt().getTime();

            if (differenceMillis > estimatedFlightTimeMillis) {
                resultFlights.add(flight);
            }
        }
        return convertToDtoFlight(resultFlights);
    }


    private long convertTimeToMillis(String time) {
        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        int seconds = Integer.parseInt(parts[2]);

        return (hours * 3600L + minutes * 60L + seconds) * 1000;
    }

    public List<FlightDtoRQ> convertToDtoFlight(List<Flight> flights) {
        return flights.stream()
                .map(flight -> modelMapper.map(flight, FlightDtoRQ.class))
                .collect(Collectors.toList());
    }

    public FlightDtoRQ convertToDtoFlight(Flight flight) {
        return modelMapper.map(flight, FlightDtoRQ.class);
    }
}
