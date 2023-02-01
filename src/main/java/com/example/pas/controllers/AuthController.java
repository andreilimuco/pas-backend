package com.example.pas.controllers;

import com.example.pas.config.JwtToken;
import com.example.pas.models.JwtRequest;
import com.example.pas.models.JwtResponse;
import com.example.pas.services.JwtCustomerDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtToken jwtToken;

    @Autowired
    private JwtCustomerDetailsService jwtCustomerDetailsService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        //validate the user credentials
        authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());

        //Get the user details for token creation
        final UserDetails customerDetails = jwtCustomerDetailsService

                .loadUserByUsername(authenticationRequest.getEmail());

        // Generates the actual string token
        final String token = jwtToken.generateToken(customerDetails);

        // Return the generated string token
        return ResponseEntity.ok(new JwtResponse(token));

    }

    private void authenticate(String email, String password) throws Exception {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }

    }

}
