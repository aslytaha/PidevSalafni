package com.example.pidev.Repositories;

import com.example.pidev.Entities.ImpayedLoans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImpayedLoansRepository extends JpaRepository<ImpayedLoans,Integer> {
    //WHERE iProjet=4
    @Query("SELECT COUNT (i) FROM ImpayedLoans i WHERE i.iProjet = :iProjet ")
    long countByIProjet(@Param("iProjet") Long iProjet);


    List<ImpayedLoans> findByiProjet(Long projectId);


    List<ImpayedLoans> findByPaymentNumber(Integer numtrans);
}
