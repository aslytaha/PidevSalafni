package com.example.pidev.Repositories;

import com.example.pidev.Entities.LoanProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanProjectRepository extends JpaRepository <LoanProject,Long> {


//    @Query("SELECT l FROM LoanProject l WHERE DetailsLoans.Id= :idprojet")
//    LoanProject getDetailByproject(@Param("idprojet") Long idprojet);
//    @Query("SELECT u FROM User u  WHERE u.idUser = ?1")
//    User getUserbyLoan(@Param("idUser") Long idUser);
//
//    @Query("SELECT l FROM LoanProject l  WHERE l.Idprojet = ?1")
//    LoanProject findByIdprojet(@Param("Idprojet") Long Idprojet);
}