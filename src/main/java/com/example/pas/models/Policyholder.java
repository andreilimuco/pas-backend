package com.example.pas.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "policyholders")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Policyholder {
    @Id
    @SequenceGenerator(
            name = "policyholder_sequence",
            sequenceName = "policyholder_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "policyholder_sequence"
    )
    private Long id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private LocalDate birthDate;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String licenseNum;
    @Column(nullable = false)
    private LocalDate licenseDateIssued;
    @OneToOne(mappedBy = "policyholder")
    @JsonIgnore
    private Policy policy;


}
