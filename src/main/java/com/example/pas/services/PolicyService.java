package com.example.pas.services;

import com.example.pas.config.JwtToken;
import com.example.pas.models.Customer;
import com.example.pas.models.Policy;
import com.example.pas.models.Policyholder;
import com.example.pas.models.Vehicle;
import com.example.pas.repositories.CustomerRepository;
import com.example.pas.repositories.PolicyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PolicyService {
    private final PolicyRepository policyRepository;
    private final CustomerRepository customerRepository;

    private final JwtToken jwtToken;

    public void createPolicy(String token, Policy policy, String firstName, String lastName, String address) {
        Customer customer = customerRepository.findByEmail(jwtToken.getEmailFromToken(token));

        Policy newPolicy = new Policy();
        newPolicy.setEffectiveDate(policy.getEffectiveDate());
        newPolicy.setExpirationDate(newPolicy.getExpirationDate());

        newPolicy.setCustomer(customer);

        Policyholder ph = new Policyholder();
        if(firstName != null && firstName.length() > 0 && !firstName.equals(customer.getFirstName())) {
            ph.setFirstName(firstName);
        } else {
            ph.setFirstName(customer.getFirstName());
        }

        if(lastName != null && lastName.length() > 0 && !lastName.equals(customer.getLastName())) {
            ph.setLastName(lastName);
        } else {
            ph.setLastName(customer.getLastName());
        }

        if(address != null && address.length() > 0 && !address.equals(customer.getAddress())) {
            ph.setAddress(address);
        } else {
            ph.setAddress(customer.getAddress());
        }

        ph.setBirthDate(policy.getPolicyholder().getBirthDate());
        ph.setLicenseNum(policy.getPolicyholder().getLicenseNum());
        ph.setLicenseDateIssued(policy.getPolicyholder().getLicenseDateIssued());

        newPolicy.setPolicyholder(ph);

        Vehicle veh = new Vehicle();
        veh.setMake(policy.getVehicles().get(0).getMake());
        veh.setModel(policy.getVehicles().get(0).getModel());
        veh.setYear(policy.getVehicles().get(0).getYear());
        veh.setType(policy.getVehicles().get(0).getType());
        veh.setFuelType(policy.getVehicles().get(0).getFuelType());
        veh.setPurchasePrice(policy.getVehicles().get(0).getPurchasePrice());
        veh.setColor(policy.getVehicles().get(0).getColor());
        veh.setPremiumCharged(policy.getVehicles().get(0).getPremiumCharged());
        veh.setPolicy(newPolicy);

        newPolicy.getVehicles().add(veh);

        newPolicy.setPolicyPremium(9999);

        policyRepository.save(newPolicy);
    }

    public Iterable<Policy> getPolicies() {
        return policyRepository.findAll();
    }

    public Optional<Policy> getPolicy(Long policyNum) {
        return policyRepository.findById(policyNum);
    }


}
