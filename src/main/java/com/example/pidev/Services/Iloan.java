package com.example.pidev.Services;

import com.example.pidev.Entities.DetailsLoans;
import com.example.pidev.Entities.LoanProject;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public interface Iloan {


    List<LoanProject> getAllLoanProjects();

    LoanProject getLoanProjectById(Long id);


    LoanProject createLoanProject(Authentication authentication, LoanProject loanProject);

    LoanProject update(LoanProject p);

    void delete(Long Idprojet);



    LoanProject addDetailsLoan(LoanProject loanProject, DetailsLoans detailsLoans);





    LoanProject updateLoanAmount(Long Idprojet, Float amountborrowed);
}
