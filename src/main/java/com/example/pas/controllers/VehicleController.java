package com.example.pas.controllers;

import com.example.pas.config.JwtToken;
import com.example.pas.models.Vehicle;
import com.example.pas.services.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("vehicles")
@CrossOrigin
public class VehicleController {
    private final VehicleService vehicleService;

    @PostMapping("/add/{policyNum}")
    public ResponseEntity<Object> addVehicle(
            @RequestHeader("Authorization") String token,
            @RequestBody Vehicle vehicle,
            @PathVariable("policyNum") Long policyNum) {

        vehicleService.addVehicle(token, vehicle, policyNum);

        return new ResponseEntity<>("Vehicle added successfully", HttpStatus.CREATED);
    }
}
