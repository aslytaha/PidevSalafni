package com.example.pidev.Repositories;

import com.example.pidev.Entities.Amortization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AmortizationRepository extends JpaRepository<Amortization,Long> {
}
