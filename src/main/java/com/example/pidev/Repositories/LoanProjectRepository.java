package com.example.pidev.Repositories;

import com.example.pidev.Entities.LoanProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanProjectRepository extends JpaRepository <LoanProject,Long> {


//    @Query("SELECT l FROM LoanProject l WHERE DetailsLoans.Id= :idprojet")
//    LoanProject getDetailByproject(@Param("idprojet") Long idprojet);


}