package com.example.pas.repositories;

import com.example.pas.models.Policyholder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PolicyholderRepository extends CrudRepository<Policyholder, Long> {
}
