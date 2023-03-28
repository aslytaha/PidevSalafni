package com.example.pidev.services;

import com.example.pidev.Entities.DetailsLoans;
import com.example.pidev.Entities.LoanProject;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public interface Iloan {


    List<LoanProject> getAllLoanProjects();

    LoanProject getLoanProjectById(Long id);

    LoanProject add(LoanProject p);

    LoanProject update(LoanProject p);

    void delete(Long Idprojet);

//    int countborrowerByProject(Float amountborrowed);


    //

}
