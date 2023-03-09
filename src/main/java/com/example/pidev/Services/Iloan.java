package com.example.pidev.Services;

import com.example.pidev.Entities.DetailsLoans;
import com.example.pidev.Entities.LoanProject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface Iloan {


    //    @Override
    //    public LoanProject updateproject(LoanProject e) {
    //        return loanProjectRepository.save(e);
    //    }
    List<LoanProject> getAllLoanProjects();

    LoanProject getLoanProjectById(Long id);

    LoanProject add(LoanProject p);

    LoanProject update(LoanProject p);

    void delete(Long Idprojet);
}
