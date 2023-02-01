package com.example.pas.services;

import com.example.pas.models.Policyholder;
import com.example.pas.repositories.PolicyholderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PolicyholderService {
    private final PolicyholderRepository phRepository;

    public void addPolicyholder(Policyholder ph) {
        phRepository.save(ph);
    }

    public Optional<Policyholder> getPolicyholder(Long phId) {
        return phRepository.findById(phId);
    }



}
