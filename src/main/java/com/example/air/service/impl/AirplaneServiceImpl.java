package com.example.air.service.impl;

import com.example.air.dto.rq.AirplaneDtoRQ;
import com.example.air.dto.rq.MoveAirplaneToCompanyDtoRq;
import com.example.air.entity.AirCompany;
import com.example.air.entity.Airplane;
import com.example.air.repository.AirCompanyRepository;
import com.example.air.repository.AirplaneRepository;
import com.example.air.service.AirplaneService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

import static com.example.air.tools.ConvertorObjects.convertToDtoAirplane;
import static com.example.air.tools.ConvertorObjects.convertToEntytyAirplane;
import static com.example.air.tools.ExceptionHandler.exceptionHandlerRequest;


@Service
@Log4j2
public class AirplaneServiceImpl implements AirplaneService {
    private final AirplaneRepository airplaneRepository;
    private final AirCompanyRepository airCompanyRepository;

    private final ModelMapper modelMapper;

    public AirplaneServiceImpl(AirplaneRepository airplaneRepository, AirCompanyRepository airCompanyRepository, ModelMapper modelMapper) {
        this.airplaneRepository = airplaneRepository;
        this.airCompanyRepository = airCompanyRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public AirplaneDtoRQ addAirplane(AirplaneDtoRQ airplane) {
        log.info("addAirplane");
        Airplane airplaneRs = airplaneRepository.save(convertToEntytyAirplane(airplane, modelMapper));
        return convertToDtoAirplane(airplaneRs, modelMapper);
    }

    @Override
    @Transactional
    public String moveAirplaneToCompany(MoveAirplaneToCompanyDtoRq rq) {
        try {
            log.info("moveAirplaneToCompany");
            Airplane airplane = airplaneRepository.findById(rq.getAirplaneId())
                    .orElseThrow(() -> new EntityNotFoundException("Airplane with this id is not found"));
            AirCompany airCompanyNew = airCompanyRepository.findById(rq.getCompanyId())
                    .orElseThrow(() -> new EntityNotFoundException("AirCompany with this id is not found"));
            airplane.setAirCompany(airCompanyNew);
            airplaneRepository.save(airplane);

            return String.format("Airplane: %s, moved to company: %s, with ID %d",
                    airplane.getName(), airCompanyNew.getName(), rq.getCompanyId());
        } catch (Exception e) {
            throw exceptionHandlerRequest(e);
        }
    }

}
