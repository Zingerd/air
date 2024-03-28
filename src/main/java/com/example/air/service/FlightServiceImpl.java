package com.example.air.service;


import com.example.air.entity.Flight;
import com.example.air.repository.FlightRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.example.air.tools.Constant.*;


@Service
public class FlightServiceImpl {
    private final FlightRepository flightRepository;

    public FlightServiceImpl(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public List<Flight> findActiveFlights() {
        return flightRepository.findAllByFlightStatusAndStartedAtBefore(ACTIVE, new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000));
    }

    public Flight addFlight(Flight flight) {
         return flightRepository.save(flight);
    }

    public ResponseEntity<Flight> changeFlightStatus(Long flightId, String status) {
        Optional<Flight> flightOptional = flightRepository.findById(flightId);

        if (flightOptional.isPresent()) {
            Flight flight = flightOptional.get();

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
                    return ResponseEntity.badRequest().build();
            }

            flightRepository.save(flight);
            return ResponseEntity.ok(flight);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    public List<Flight> getCompletedFlightsWithTimeDifference() {
        List<Flight> completedFlights = flightRepository.findAllByFlightStatus(COMPLETED);
        List<Flight> resultFlights = new ArrayList<>();

        for (Flight flight : completedFlights) {
            long estimatedFlightTimeMillis = convertTimeToMillis(flight.getEstimatedFlightTime());
            long differenceMillis = flight.getEndedAt().getTime() - flight.getStartedAt().getTime();

            if (differenceMillis > estimatedFlightTimeMillis) {
                resultFlights.add(flight);
            }
        }
        return resultFlights;
    }


    private long convertTimeToMillis(String time) {
        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        int seconds = Integer.parseInt(parts[2]);

        return (hours * 3600L + minutes * 60L + seconds) * 1000;
    }
}
