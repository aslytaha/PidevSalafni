package com.example.pidev.Repositories;

import com.example.pidev.Entities.LoanProject;
import com.example.pidev.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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