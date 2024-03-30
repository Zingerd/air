package com.example.air.tools;

import com.example.air.dto.rq.AirCompanyDtoRQ;
import com.example.air.dto.rq.AirplaneDtoRQ;
import com.example.air.dto.rq.FlightDtoRQ;
import com.example.air.entity.AirCompany;
import com.example.air.entity.Airplane;
import com.example.air.entity.Flight;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;


public class ConvertorObjects {


    public static List<AirCompanyDtoRQ> convertToDtoAirCompany(List<AirCompany> airCompanies, ModelMapper modelMapper) {
        return airCompanies.stream()
                .map(airplane -> modelMapper.map(airplane, AirCompanyDtoRQ.class))
                .collect(Collectors.toList());
    }

    public static AirCompanyDtoRQ convertToDtoAirCompany(AirCompany airCompanies, ModelMapper modelMapper) {
        return modelMapper.map(airCompanies, AirCompanyDtoRQ.class);
    }

    public static List<FlightDtoRQ> convertToDtoFlightList(List<Flight> flights, ModelMapper modelMapper) {
        return flights.stream()
                .map(flight -> modelMapper.map(flight, FlightDtoRQ.class))
                .collect(Collectors.toList());
    }

    public static AirCompany convertToEntytyAirCompany(AirCompanyDtoRQ airCompanies, ModelMapper modelMapper) {
        return modelMapper.map(airCompanies, AirCompany.class);
    }

    public static AirplaneDtoRQ convertToDtoAirplane(Airplane airplane, ModelMapper modelMapper) {
        return modelMapper.map(airplane, AirplaneDtoRQ.class);
    }

    public static Airplane convertToEntytyAirplane(AirplaneDtoRQ airplane, ModelMapper modelMapper) {
        return modelMapper.map(airplane, Airplane.class);
    }


    public static FlightDtoRQ convertToDtoFlight(Flight flight, ModelMapper modelMapper) {
        return modelMapper.map(flight, FlightDtoRQ.class);
    }

    public static Flight convertToEntytyFlight(FlightDtoRQ flight, ModelMapper modelMapper) {
        return modelMapper.map(flight, Flight.class);
    }

}
