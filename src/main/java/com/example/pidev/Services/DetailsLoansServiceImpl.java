package com.example.pidev.Services;


import com.example.pidev.Entities.DetailsLoans;
import com.example.pidev.Repositories.DetailsLoansRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public  class DetailsLoansServiceImpl implements Idetails{

    private DetailsLoansRepository detailsLoansRepository;

    @Override
    public List<DetailsLoans> getAllDetails() {
        return null;
    }

    @Override
    public DetailsLoans getDetailsLoansById(Integer id) {
//        return detailsLoansRepository.findById(id).orElse(null);
        return detailsLoansRepository.findById(id).get();
    }
    @Override
    public DetailsLoans update(DetailsLoans p) {
        return detailsLoansRepository.save(p);
    }























}
