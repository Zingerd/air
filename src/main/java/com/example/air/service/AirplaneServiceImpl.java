package com.example.air.service;

import com.example.air.dto.rq.AirplaneDtoRQ;
import com.example.air.entity.AirCompany;
import com.example.air.entity.Airplane;
import com.example.air.repository.AirCompanyRepository;
import com.example.air.repository.AirplaneRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service
public class AirplaneServiceImpl {
    private final AirplaneRepository airplaneRepository;
    private final AirCompanyRepository airCompanyRepository;

    private final ModelMapper modelMapper;

    public AirplaneServiceImpl(AirplaneRepository airplaneRepository, AirCompanyRepository airCompanyRepository, ModelMapper modelMapper) {
        this.airplaneRepository = airplaneRepository;
        this.airCompanyRepository = airCompanyRepository;
        this.modelMapper = modelMapper;
    }

    public AirplaneDtoRQ addAirplane(Airplane airplane) {
        return convertToDtoAirplane(airplaneRepository.save(airplane));
    }

    public String moveAirplaneToCompany(Long airplaneId, Long companyId) {
        Airplane airplane = airplaneRepository.findById(airplaneId).orElseThrow();
        AirCompany airCompanyNew = airCompanyRepository.findById(companyId).orElseThrow();

        airplane.setAirCompany(airCompanyNew);
        airplaneRepository.save(airplane);

        return "Airplane moved to company: " + airCompanyNew.getName() +
                ", with ID:" + companyId;
    }

    public AirplaneDtoRQ convertToDtoAirplane(Airplane airplane) {
        return modelMapper.map(airplane, AirplaneDtoRQ.class);
    }
}
