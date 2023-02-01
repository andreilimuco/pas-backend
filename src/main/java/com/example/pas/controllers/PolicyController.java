package com.example.pas.controllers;

import com.example.pas.config.JwtToken;
import com.example.pas.models.Customer;
import com.example.pas.models.Policy;
import com.example.pas.models.Policyholder;
import com.example.pas.models.Vehicle;
import com.example.pas.services.CustomerService;
import com.example.pas.services.PolicyService;
import com.example.pas.services.PolicyholderService;
import com.example.pas.services.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("policies")
@CrossOrigin
public class PolicyController {
    private final PolicyService policyService;
    private final CustomerService customerService;
    private final PolicyholderService phService;
    private final VehicleService vehicleService;

    @Autowired
    private final JwtToken jwtToken;

    @PostMapping("/add")
    public ResponseEntity<Object> createPolicy(
            @RequestHeader("Authorization") String token,
            @RequestBody Policy policy,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String address) {
//        Customer cus = customerService.findByEmail(jwtToken.getEmailFromToken(token)).get();
//
//
//        Policy newPolicy = new Policy();
//        newPolicy.setEffectiveDate(policy.getEffectiveDate());
//        newPolicy.setExpirationDate(newPolicy.getExpirationDate());
//
//        newPolicy.setCustomer(cus);
////        cus.getPolicies().add(newPolicy);
//
//        Policyholder ph = new Policyholder();
//        ph.setFirstName(cus.getFirstName());
//        ph.setLastName(cus.getLastName());
//        ph.setAddress(cus.getAddress());
////        ph.setFirstName(policy.getPolicyholder().getFirstName());
////        ph.setLastName(policy.getPolicyholder().getLastName());
////        ph.setAddress(policy.getPolicyholder().getAddress());
//        ph.setBirthDate(policy.getPolicyholder().getBirthDate());
//        ph.setLicenseNum(policy.getPolicyholder().getLicenseNum());
//        ph.setLicenseDateIssued(policy.getPolicyholder().getLicenseDateIssued());
////        ph.setPolicy(newPolicy);
//
//        newPolicy.setPolicyholder(ph);
//
//        Vehicle veh = new Vehicle();
//        veh.setMake(policy.getVehicles().get(0).getMake());
//        veh.setModel(policy.getVehicles().get(0).getModel());
//        veh.setYear(policy.getVehicles().get(0).getYear());
//        veh.setPolicy(newPolicy);
//
//        newPolicy.getVehicles().add(veh);
//
//        newPolicy.setPolicyPremium(9999);

        policyService.createPolicy(token, policy, firstName, lastName, address);
        return new ResponseEntity<>("Policy created successfully", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Object> getPolicies() {
        return ResponseEntity.ok(policyService.getPolicies());
    }

    @GetMapping("/{policyNum}")
    public ResponseEntity<Object> getPolicy(@PathVariable Long policyNum) {
        return ResponseEntity.ok(policyService.getPolicy(policyNum));
    }



}
