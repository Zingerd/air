package com.example.air.service;

import com.example.air.dto.rq.AirCompanyDtoRQ;
import com.example.air.dto.rq.FlightDtoRQ;

import java.util.List;

public interface AirCompaniesService {
    List<AirCompanyDtoRQ> getAllAirCompanies();
    AirCompanyDtoRQ getAirCompanyById(Long id);
    AirCompanyDtoRQ createAirCompany(AirCompanyDtoRQ airCompany);
    AirCompanyDtoRQ updateAirCompany(Long id, AirCompanyDtoRQ updatedAirCompany);
    String deleteAirCompany(Long id);
    List<FlightDtoRQ> getFlightsByAirCompanyAndStatus(String companyName, String status);

}
