package com.example.pidev.Services;

import com.example.pidev.Entities.Assurance;
import com.example.pidev.Interfaces.IAssuranceService;
import com.example.pidev.Repositories.AssuranceRepository;
import com.example.pidev.Repositories.LoanProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class AssuranceService implements IAssuranceService {
    AssuranceRepository assuranceRepository;
    LoanProjectRepository loanProjectRepository;
    @Override
    public Assurance addAssurance(Assurance as) {
        return assuranceRepository.save(as);
    }

    @Override
    public Assurance updateAssurance(Assurance as) {
        return assuranceRepository.save(as);
    }

    @Override
    public List<Assurance> getAllAssurance() {
        return assuranceRepository.findAll();
    }

    @Override
    public Assurance getAssuranceById(Integer asID) {
        return assuranceRepository.findById(asID).orElse(null);
    }

    @Override
    public void deleteAssurance(Integer asID) {
        assuranceRepository.deleteById(asID);
    }

   // @Override
    //public Assurance addAssuranceAndAssignToLoanProject(Assurance assurance, Long Idprojet) {
      //  Assurance a= assuranceRepository.save(assurance);
        //LoanProject lp = loanProjectRepository.findById(Idprojet).get();
        //a.getLoanProjects();
        //return assuranceRepository.save(a);
    //}
}
