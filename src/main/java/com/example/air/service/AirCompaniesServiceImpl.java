package com.example.air.service;


import com.example.air.dto.rq.AirCompanyDtoRQ;
import com.example.air.dto.rq.FlightDtoRQ;
import com.example.air.entity.AirCompany;
import com.example.air.entity.Flight;
import com.example.air.repository.AirCompanyRepository;
import com.example.air.repository.FlightRepository;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class AirCompaniesServiceImpl {
    private final AirCompanyRepository airCompanyRepository;
    private final FlightRepository flightRepository;
    private final ModelMapper modelMapper;

    public AirCompaniesServiceImpl(AirCompanyRepository airCompanyRepository, FlightRepository flightRepository,
                                   ModelMapper modelMapper) {
        this.airCompanyRepository = airCompanyRepository;
        this.flightRepository = flightRepository;
        this.modelMapper = modelMapper;
    }

    public List<AirCompanyDtoRQ> getAllAirCompanies() {
        log.info("getAllAirCompanies");
        List<AirCompany> airCompanies = airCompanyRepository.findAll();
        return convertToDtoAirCompany(airCompanies);
    }

    public AirCompanyDtoRQ getAirCompanyById(Long id) {
        log.info("getAirCompanyById");
        AirCompany airCompany = airCompanyRepository.findById(id).orElseThrow();
        AirCompanyDtoRQ rs = convertToDtoAirCompany(airCompany);
        return rs;
    }

    public AirCompanyDtoRQ createAirCompany(AirCompany airCompany) {
        log.info("createAirCompany");
        AirCompany airCompanyRs = airCompanyRepository.save(airCompany);
        return convertToDtoAirCompany(airCompanyRs);
    }

    public AirCompanyDtoRQ updateAirCompany(Long id, AirCompany updatedAirCompany) {
        log.info("updateAirCompany");
        AirCompany existingAirCompany = airCompanyRepository.findById(id).orElseThrow();
        existingAirCompany.setName(updatedAirCompany.getName());
        existingAirCompany.setCompanyType(updatedAirCompany.getCompanyType());
        existingAirCompany.setFoundedAt(updatedAirCompany.getFoundedAt());
        airCompanyRepository.save(existingAirCompany);
        return convertToDtoAirCompany(existingAirCompany);

    }

    public void deleteAirCompany(Long id) {
        log.info("deleteAirCompany");
        airCompanyRepository.deleteById(id);
    }

    public List<FlightDtoRQ> getFlightsByAirCompanyAndStatus(String companyName, String status) {
        log.info("getFlightsByAirCompanyAndStatus");
        AirCompany airCompanyOptional = airCompanyRepository.findByName(companyName).orElseThrow();
        AirCompanyDtoRQ airCompanyDtoRQ = convertToDtoAirCompany(airCompanyOptional);

        List<Flight> flights = flightRepository.findByAirCompanyAndFlightStatus(airCompanyOptional, status);

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
}
