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

import static com.example.air.tools.ConvertorObjects.convertToDtoAirCompany;
import static com.example.air.tools.ConvertorObjects.convertToDtoFlightList;
import static com.example.air.tools.ConvertorObjects.convertToEntytyAirCompany;
import static com.example.air.tools.ExceptionHandler.exceptionHandlerRequest;

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
        return convertToDtoAirCompany(airCompanies, modelMapper);
    }

    @Override
    public AirCompanyDtoRQ getAirCompanyById(Long id) {
        try {

            log.info("getAirCompanyById");
            AirCompany airCompany = airCompanyRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("AirCompany not found with id: " + id));
            return convertToDtoAirCompany(airCompany, modelMapper);
        } catch (Exception e) {
            throw exceptionHandlerRequest(e);
        }
    }

    @Override
    public AirCompanyDtoRQ createAirCompany(AirCompanyDtoRQ airCompany) {
        log.info("createAirCompany");
        AirCompany airCompanyRs = convertToEntytyAirCompany(airCompany, modelMapper);
        airCompanyRepository.save(airCompanyRs);
        return convertToDtoAirCompany(airCompanyRs, modelMapper);
    }

    @Override
    public AirCompanyDtoRQ updateAirCompany(Long id, AirCompanyDtoRQ updatedAirCompany) {
        try {
            log.info("updateAirCompany");
            AirCompany existingAirCompany = airCompanyRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("AirCompany not found with id: " + id));
            existingAirCompany.setName(updatedAirCompany.getName());
            existingAirCompany.setCompanyType(updatedAirCompany.getCompanyType());
            existingAirCompany.setFoundedAt(updatedAirCompany.getFoundedAt());
            airCompanyRepository.save(existingAirCompany);
            return convertToDtoAirCompany(existingAirCompany, modelMapper);
        } catch (Exception e) {
            throw exceptionHandlerRequest(e);
        }
    }

    @Override
    public String deleteAirCompany(Long id) throws ExceptionHandler {
        try {
            log.info("deleteAirCompany");
            AirCompany airCompany = airCompanyRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("AirCompany not found with id: " + id));
            airCompanyRepository.deleteById(id);
            return String.format("Company: %s, with this id: %s, removed", airCompany.getName(), id);
        } catch (Exception e) {
            throw exceptionHandlerRequest(e);
        }
    }

    @Override
    public List<FlightDtoRQ> getFlightsByAirCompanyAndStatus(String companyName, String status) {
        try {
            log.info("getFlightsByAirCompanyAndStatus");
            AirCompany airCompany = airCompanyRepository.findByName(companyName)
                    .orElseThrow(() -> new EntityNotFoundException("AirCompany not found with name: " + companyName));
            List<Flight> flights = flightRepository.findByAirCompanyAndFlightStatus(airCompany, status)
                    .orElseThrow(() -> new EntityNotFoundException("Flights with this status is not found"));
            return convertToDtoFlightList(flights, modelMapper);
        } catch (Exception e) {
            throw exceptionHandlerRequest(e);
        }
    }
}
