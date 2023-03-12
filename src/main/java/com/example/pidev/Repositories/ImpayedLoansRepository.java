package com.example.pidev.Repositories;

import com.example.pidev.Entities.ImpayedLoans;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImpayedLoansRepository extends JpaRepository<ImpayedLoans,Integer> {
}
