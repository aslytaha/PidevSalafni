package com.example.pidev.Interfaces;

import com.example.pidev.Entities.DetailsLoans;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface Idetails {
    List<DetailsLoans> getAllDetails();


    DetailsLoans getDetailsLoansById(Integer id);


    DetailsLoans updateD(DetailsLoans p);

    DetailsLoans addD(DetailsLoans de);
}
