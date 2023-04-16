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

    //    @Transactional
    //    public LoanProject updateLoanAmount(Long id, DetailsLoans details) {
    //        LoanProject project = projectRepository.findById(id)
    //                .orElseThrow(() -> new EntityNotFoundException("Project not found with id: " + id));
    //
    //        float amountBorrowed = details.getAmountborrowed();
    //        float remainingAmount = project.getLoanamount() - amountBorrowed;
    //
    //        if (remainingAmount < 0) {
    //            throw new IllegalArgumentException("Amount borrowed cannot exceed loan amount");
    //        }
    //
    //        project.setLoanamount(remainingAmount);
    //        details.setLoanProject(project);
    //        detail.save(details);
    //
    //        return project;
    //    }
    void addRemainingAmountToLoanProject();
    //   Map<Long, List<User>> getAllBorrowers();
   // List<String> getAllProjectOwners();

//    int countborrowerByProject(Float amountborrowed);


    //

}
