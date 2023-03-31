package com.example.pidev.Repositories;

import com.example.pidev.Entities.Assurance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssuranceRepository extends JpaRepository<Assurance,Integer> {
    Assurance findByname(String name);
}
