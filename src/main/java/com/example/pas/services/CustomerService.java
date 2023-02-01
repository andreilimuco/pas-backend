package com.example.pas.services;

import com.example.pas.models.Customer;
import com.example.pas.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public Optional<Customer> findByEmail(String email) {
        return Optional.ofNullable(customerRepository.findByEmail(email));
    }

    public Iterable<Customer> getCustomers() {
        return customerRepository.findAll();
    }
    public void createCustomer(Customer customer) {
        customerRepository.save(customer);
    }
}
