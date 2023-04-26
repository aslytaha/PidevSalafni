package com.example.pidev.Interfaces;

import com.example.pidev.Entities.LoanProject;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public interface Iloan {


    List<LoanProject> getAllLoanProjects();



    LoanProject createLoanProject(Authentication authentication, LoanProject loanProject);

    LoanProject update(LoanProject p);

    void delete(Long Idprojet);
    LoanProject updateLoanAmount(Long Idprojet, Float amountborrowed, Authentication authentication) throws Exception;


}
