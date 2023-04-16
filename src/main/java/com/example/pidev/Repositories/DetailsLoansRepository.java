package com.example.pidev.Repositories;

import com.example.pidev.Entities.DetailsLoans;
import com.example.pidev.Entities.LoanProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailsLoansRepository extends JpaRepository<DetailsLoans,Integer> {

    //    public DetailsLoans findByIdDetails(Integer idDetails);
    void delete(DetailsLoans detail);

    @Query("DELETE FROM DetailsLoans d WHERE d.loanProject = :loanProject")
    void deleteAllByLoanProject(@Param("loanProject") LoanProject loanProject);
}
