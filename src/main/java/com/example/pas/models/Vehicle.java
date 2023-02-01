package com.example.pas.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "vehicles")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Vehicle {
    @Id
    @SequenceGenerator(
            name = "vehicle_sequence",
            sequenceName = "vehicle_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "vehicle_sequence"
    )
    private Long id;
    @Column(nullable = false)
    private String make;
    @Column(nullable = false)
    private String model;
    @Column(nullable = false)
    private int year;
    @Column(nullable = false)
    private String type;
    @Column(nullable = false)
    private String fuelType;
    @Column(nullable = false)
    private double purchasePrice;
    @Column(nullable = false)
    private String color;
    @Column(nullable = false)
    private double premiumCharged;
    @ManyToOne
    @JoinColumn(name = "policy_id", nullable = false)
    @JsonIgnore
    private Policy policy;

}
