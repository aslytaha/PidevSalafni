package com.example.pidev.Services;


import com.example.pidev.Entities.LoanProject;
import com.example.pidev.Repositories.LoanProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public  class LoanProjectServiceImpl implements Iloan {

    LoanProjectRepository projectRepository;


    @Override
    public List<LoanProject> getAllLoanProjects() {
        return projectRepository.findAll();
    }

    @Override
    public LoanProject getLoanProjectById(Long id) {
        return projectRepository.findById(id).get();
    }
    @Override
    public LoanProject add(LoanProject p) {
        return projectRepository.save(p);
    }

    @Override
    public LoanProject update(LoanProject p) {
        return projectRepository.save(p);
    }



    @Override
    public void delete(Long Idprojet) {
        projectRepository.deleteById(Idprojet);

    }
}




