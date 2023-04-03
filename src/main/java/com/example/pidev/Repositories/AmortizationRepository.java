package com.example.pidev.Repositories;

import com.example.pidev.Entities.Amortization;
import com.example.pidev.Entities.DetailsLoans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface AmortizationRepository extends JpaRepository<Amortization,Long> {
   // @Query("SELECT a FROM Amortization a WHERE a.status = 'NOTPAYED' AND a.paymentDate < :now")
    //List<Amortization> findImpayedLoans(@Param("now") LocalDate now);
}
