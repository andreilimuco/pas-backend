package com.example.pas.repositories;

import com.example.pas.models.Policy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface PolicyRepository extends CrudRepository<Policy, Long> {
}
