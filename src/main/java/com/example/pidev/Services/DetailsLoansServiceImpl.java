package com.example.pidev.Services;


import com.example.pidev.Entities.DetailsLoans;
import com.example.pidev.Interfaces.Idetails;
import com.example.pidev.Repositories.DetailsLoansRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public  class DetailsLoansServiceImpl implements Idetails {
@Autowired
    private DetailsLoansRepository detailsLoansRepository;


    @Override
    public List<DetailsLoans> getAllDetails() {
        return detailsLoansRepository.findAll();
    }


    @Override
    public DetailsLoans getDetailsLoansById(Integer id) {
//        return detailsLoansRepository.findById(id).orElse(null);
        return detailsLoansRepository.findById(id).get();
    }
    @Override
    public DetailsLoans updateD(DetailsLoans p) {
        return detailsLoansRepository.save(p);
    }
    @Override
    public DetailsLoans addD(DetailsLoans de) {

        return detailsLoansRepository.save(de);
    }






















}
