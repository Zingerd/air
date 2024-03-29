package com.example.air.service.impl;


import com.example.air.dto.rq.AirCompanyDtoRQ;
import com.example.air.dto.rq.FlightDtoRQ;
import com.example.air.entity.AirCompany;
import com.example.air.entity.Flight;
import com.example.air.repository.AirCompanyRepository;
import com.example.air.repository.FlightRepository;
import com.example.air.service.AirCompaniesService;
import com.example.air.tools.ExceptionHandler;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class AirCompaniesServiceImpl implements AirCompaniesService {
    private final AirCompanyRepository airCompanyRepository;
    private final FlightRepository flightRepository;
    private final ModelMapper modelMapper;

    public AirCompaniesServiceImpl(AirCompanyRepository airCompanyRepository, FlightRepository flightRepository,
                                   ModelMapper modelMapper) {
        this.airCompanyRepository = airCompanyRepository;
        this.flightRepository = flightRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<AirCompanyDtoRQ> getAllAirCompanies() {
        log.info("getAllAirCompanies");
        List<AirCompany> airCompanies = airCompanyRepository.findAll();
        return convertToDtoAirCompany(airCompanies);
    }

    @Override
    public AirCompanyDtoRQ getAirCompanyById(Long id) {
        log.info("getAirCompanyById");
        AirCompany airCompany = airCompanyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("AirCompany not found with id: " + id));
        return convertToDtoAirCompany(airCompany);
    }

    @Override
    public AirCompanyDtoRQ createAirCompany(AirCompanyDtoRQ airCompany) {
        log.info("createAirCompany");
        AirCompany airCompanyRs = convertToEntytyAirCompany(airCompany);
        airCompanyRepository.save(airCompanyRs);
        return convertToDtoAirCompany(airCompanyRs);
    }

    @Override
    public AirCompanyDtoRQ updateAirCompany(Long id, AirCompanyDtoRQ updatedAirCompany) {
        log.info("updateAirCompany");
        AirCompany existingAirCompany = airCompanyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("AirCompany not found with id: " + id));
        existingAirCompany.setName(updatedAirCompany.getName());
        existingAirCompany.setCompanyType(updatedAirCompany.getCompanyType());
        existingAirCompany.setFoundedAt(updatedAirCompany.getFoundedAt());
        airCompanyRepository.save(existingAirCompany);
        return convertToDtoAirCompany(existingAirCompany);
    }

    @Override
    public String deleteAirCompany(Long id) throws ExceptionHandler {
        log.info("deleteAirCompany");
        AirCompany airCompany =  airCompanyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("AirCompany not found with id: " + id));

        airCompanyRepository.deleteById(id);
        return String.format("Company: %s, with this id: %s, removed", airCompany.getName(), id);
    }

    @Override
    public List<FlightDtoRQ> getFlightsByAirCompanyAndStatus(String companyName, String status) {
        log.info("getFlightsByAirCompanyAndStatus");
        AirCompany airCompany = airCompanyRepository.findByName(companyName)
                .orElseThrow(() -> new EntityNotFoundException("AirCompany not found with name: " + companyName));
        List<Flight> flights = flightRepository.findByAirCompanyAndFlightStatus(airCompany, status)
                .orElseThrow(() -> new EntityNotFoundException("Flights with this status is not found"));
        return convertToDtoFlight(flights);
    }


    private List<AirCompanyDtoRQ> convertToDtoAirCompany(List<AirCompany> airCompanies) {
        return airCompanies.stream()
                .map(airplane -> modelMapper.map(airplane, AirCompanyDtoRQ.class))
                .collect(Collectors.toList());
    }

    private AirCompanyDtoRQ convertToDtoAirCompany(AirCompany airCompanies) {
        return modelMapper.map(airCompanies, AirCompanyDtoRQ.class);
    }

    private List<FlightDtoRQ> convertToDtoFlight(List<Flight> flights) {
        return flights.stream()
                .map(flight -> modelMapper.map(flight, FlightDtoRQ.class))
                .collect(Collectors.toList());
    }

    private AirCompany convertToEntytyAirCompany(AirCompanyDtoRQ airCompanies) {
        return modelMapper.map(airCompanies, AirCompany.class);
    }
}
