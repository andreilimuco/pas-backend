package com.example.pas.services;

import com.example.pas.models.Customer;
import com.example.pas.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

//@Component is an annotation that allows Spring to automatically detect our custom classes.
@Component
// UserDetailsService- retrieves the user's authentication and authorization information
public class JwtCustomerDetailsService implements UserDetailsService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    // UserDetails-> store information which later encapsulated into Authentication objects, this allows non-security related user information(email, telephone number, etc) to be stored in a convenient location
    // loadUserByUsername-> method from UserDetails, locates the user based on the username
    // throws-> declare the exceptions that can occur during the execution of the program
    // UsernameNotFoundException-> thrown when a user cannot be located by its username
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByEmail(email);
        if (customer == null) {
            throw new UsernameNotFoundException("Customer not found with email: " + email);
        }
        return new org.springframework.security.core.userdetails.User(customer.getEmail(), customer.getPassword(),
                new ArrayList<>());
    }
}
