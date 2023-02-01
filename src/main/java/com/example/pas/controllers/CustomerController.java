package com.example.pas.controllers;

import com.example.pas.models.Customer;
import com.example.pas.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("customers")
@CrossOrigin
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<Object> getCustomers() {
        return ResponseEntity.ok(customerService.getCustomers());
    }

    @PostMapping("/register")
    public ResponseEntity<Object> createCustomer(@RequestBody Map<String, String> body) throws Exception {
        String email = body.get("email");

        if(customerService.findByEmail(email).isPresent()) {
            throw new Exception("Email already exists.");
        } else {
            String password = body.get("password");
            String firstName = body.get("firstName");
            String lastName = body.get("lastName");
            String address = body.get("address");

            String encodedPassword = new BCryptPasswordEncoder().encode(password);

            Customer newCustomer = new Customer(email, encodedPassword, firstName, lastName, address);
            customerService.createCustomer(newCustomer);
        }
        return new ResponseEntity<>("Customer added successfully", HttpStatus.CREATED);
    }


}
