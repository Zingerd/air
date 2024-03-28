package com.example.air.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Table()
@Entity(name = "air_company")
@Getter
@Setter
@NoArgsConstructor
public class AirCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "company_type")
    private String companyType;
    @Column(name = "founded_at")
    private Date foundedAt;
}
