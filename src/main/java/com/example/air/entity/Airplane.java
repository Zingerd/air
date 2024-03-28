package com.example.air.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
@Table
@Entity(name = "airplane")
@Getter
@Setter
public class Airplane {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "factory_serial_number")
    private String factorySerialNumber;
    @ManyToOne
    @JoinColumn(name = "air_company_id", referencedColumnName = "id")
    private AirCompany airCompany;
    @Column(name = "number_of_flights")
    private Integer numberOfFlights;
    @Column(name = "flight_distance")
    private Float flightDistance;
    @Column(name = "fuel_capacity")
    private Float fuelCapacity;
    @Column(name = "type")
    private String type;
    @Column(name = "created_at")
    private Date createdAt;

}
