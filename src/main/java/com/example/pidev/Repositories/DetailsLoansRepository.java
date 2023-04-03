package com.example.pidev.Repositories;

import com.example.pidev.Entities.DetailsLoans;
import com.example.pidev.Entities.ImpayedLoans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface DetailsLoansRepository extends JpaRepository<DetailsLoans,Integer> {

    //    public DetailsLoans findByIdDetails(Integer idDetails);
    void delete(DetailsLoans detail);
//    @Query("FROM DetailsLoans d WHERE d.amountborrowed = :amountborrowed")
//    List<DetailsLoans> countProductByType(@Param(value = "amountborrowed") Float amountborrowed);
@Query("SELECT d FROM DetailsLoans d WHERE d.Status = 'NOTPAYED' AND d.RefundDate < :now")
List<DetailsLoans> findImpayedLoans(@Param("now") LocalDate now);

  //  DetailsLoans findByNumDetails(Long numDetails);

    //  List<ImpayedLoans> findImpayedLoansByClientId(Long clientId);
    
}
