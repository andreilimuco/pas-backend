package com.example.pas.services;

import com.example.pas.config.JwtToken;
import com.example.pas.models.Customer;
import com.example.pas.models.Policy;
import com.example.pas.models.Vehicle;
import com.example.pas.repositories.CustomerRepository;
import com.example.pas.repositories.PolicyRepository;
import com.example.pas.repositories.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VehicleService {
    private final VehicleRepository vehicleRepository;

    private final JwtToken jwtToken;
    private final CustomerRepository customerRepository;

    private final PolicyRepository policyRepository;

//    public void addVehicles(Iterable<Vehicle> vehicles) {
//        vehicleRepository.saveAll(vehicles);
//    }

    public void addVehicle(String token, Vehicle vehicle, Long policyNum) {
        Customer customer = customerRepository.findByEmail(jwtToken.getEmailFromToken(token));
        Policy policy = policyRepository.findById(policyNum).get();

        Vehicle newVehicle = new Vehicle();
        newVehicle.setMake(vehicle.getMake());
        newVehicle.setModel(vehicle.getModel());
        newVehicle.setYear(vehicle.getYear());
        newVehicle.setType(vehicle.getType());
        newVehicle.setFuelType(vehicle.getFuelType());
        newVehicle.setPurchasePrice(vehicle.getPurchasePrice());
        newVehicle.setColor(vehicle.getColor());
        newVehicle.setPremiumCharged(vehicle.getPremiumCharged());
        newVehicle.setPolicy(policy);

        vehicleRepository.save(newVehicle);
    }

    public Iterable<Vehicle> getVehicles() {
        return vehicleRepository.findAll();
    }

}
