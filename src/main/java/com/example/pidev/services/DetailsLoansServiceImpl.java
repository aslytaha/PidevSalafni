package com.example.pidev.services;


import com.example.pidev.Entities.DetailsLoans;
import com.example.pidev.Entities.ImpayedLoans;
import com.example.pidev.Repositories.DetailsLoansRepository;
import com.example.pidev.Repositories.ImpayedLoansRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
//@NoArgsConstructor
public  class DetailsLoansServiceImpl implements Idetails {

    private DetailsLoansRepository detailsLoansRepository;
    ImpayedLoansRepository impayedLoansRepository;

    @Override
    public List<DetailsLoans> getAllDetails() {

        return null;
    }

    @Override
    public DetailsLoans getDetailsLoansById(Integer id) {
//        return detailsLoansRepository.findById(id).orElse(null);
        return detailsLoansRepository.findById(id).get();
    }

 //   @Override
   // public String findProjectNameByName(String projectname) {
     //   DetailsLoans detailsLoans = detailsLoansRepository.findByprojectname(numDetails);
       // return detailsLoans.getLoanProject().getProjectname();
    //}



    //  @Override
    //public DetailsLoans updateD(DetailsLoans p) {
//
  //      return detailsLoansRepository.save(p);
    //}

    @Override
    public DetailsLoans add(DetailsLoans de) {
        return detailsLoansRepository.save(de);
    }
}