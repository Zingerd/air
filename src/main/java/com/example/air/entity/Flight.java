package com.example.air.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
@Table
@Entity(name = "flight" )
@Getter
@Setter
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "flight_status")
    private String flightStatus;
    @ManyToOne
    @JoinColumn(name = "air_company_id", referencedColumnName = "id")
    private AirCompany airCompany;
    @ManyToOne
    @JoinColumn(name = "airplane_id", referencedColumnName = "id")
    private Airplane airplane;
    @Column(name = "departure_country")
    private String departureCountry;
    @Column(name = "destination_country")
    private String destinationCountry;
    @Column(name = "distance")
    private Float distance;
    @Column(name = "estimated_flight_time")
    private String estimatedFlightTime;
    @Column(name = "started_at")
    private Date startedAt;
    @Column(name = "ended_at")
    private Date endedAt;
    @Column(name = "delay_started_at")
    private Date delayStartedAt;
    @Column(name = "created_at")
    private Date createdAt;

}
