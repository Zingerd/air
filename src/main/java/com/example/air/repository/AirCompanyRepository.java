package com.example.air.repository;

import com.example.air.entity.AirCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AirCompanyRepository extends JpaRepository<AirCompany,Long> {
    Optional<AirCompany> findByName(String name);
}
