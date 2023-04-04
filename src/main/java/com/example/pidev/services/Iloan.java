package com.example.pidev.services;

import com.example.pidev.Entities.DetailsLoans;
import com.example.pidev.Entities.LoanProject;
import com.example.pidev.Entities.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;


public interface Iloan {


    List<LoanProject> getAllLoanProjects();

    LoanProject getLoanProjectById(Long id);

    LoanProject add(LoanProject p);

    LoanProject update(LoanProject p);

    void delete(Long Idprojet);

    //  LoanProject createLoanProject(Authentication authentication, LoanProject loanProject);
   // LoanProject addDetailsLoan(LoanProject loanProject, DetailsLoans detailsLoans);
 //   LoanProject updateLoanAmount(Long Idprojet, Float amountborrowed);





    LoanProject addAssuranceToProjectByName(Long Idprojet, String assurancename);

    List<String> getAllBorrowers();
    //   Map<Long, List<User>> getAllBorrowers();
   // List<String> getAllProjectOwners();

//    int countborrowerByProject(Float amountborrowed);


    //

}
