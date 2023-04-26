package com.example.pidev.Repositories;

import com.example.pidev.Entities.DetailsLoans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailsLoansRepository extends JpaRepository<DetailsLoans,Integer> {

//    public DetailsLoans findByIdDetails(Integer idDetails);
    void delete(DetailsLoans detail);
//    @Query("FROM DetailsLoans d WHERE d.amountborrowed = :amountborrowed")
//    List<DetailsLoans> countProductByType(@Param(value = "amountborrowed") Float amountborrowed);
}
