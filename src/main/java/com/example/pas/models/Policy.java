package com.example.pas.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "policies")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Policy {
    @Id
    @SequenceGenerator(
            name = "policy_sequence",
            sequenceName = "policy_sequence",
            initialValue = 100000,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "policy_sequence"
    )
    private Long policyNum;
    @Column(nullable = false)
    private LocalDate effectiveDate;
    @Column(nullable = false)
    private LocalDate expirationDate;
    @OneToOne(cascade = CascadeType.ALL)
    private Policyholder policyholder;
    @OneToMany(mappedBy = "policy", cascade = CascadeType.ALL)
    private List<Vehicle> vehicles = new ArrayList<>();
    @Column(nullable = false)
    private double policyPremium;
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonIgnore
    private Customer customer;

    public LocalDate getExpirationDate() {
        return this.effectiveDate.plusMonths(6);
    }

}
