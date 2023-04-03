package com.example.pidev.services;

import com.example.pidev.Entities.DetailsLoans;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface Idetails {
    List<DetailsLoans> getAllDetails();
    DetailsLoans getDetailsLoansById(Integer id);
 //   DetailsLoans updateD(DetailsLoans p);
    DetailsLoans add(DetailsLoans de);
   // String findProjectNameByNumDetails(Long numDetails);

}
