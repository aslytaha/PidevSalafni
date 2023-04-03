package com.example.pidev.services;

import com.example.pidev.Entities.Amortization;
import com.example.pidev.Entities.Assurance;
import com.example.pidev.Entities.DetailsLoans;
import com.example.pidev.Entities.ImpayedLoans;
import com.example.pidev.Repositories.AmortizationRepository;
import com.example.pidev.Repositories.ImpayedLoansRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class AmortizationService implements IAmortization{
    @Autowired
    ImpayedLoansRepository impayedLoansRepository;
   @Autowired
    AmortizationRepository amortizationRepository;
  //  @Scheduled(cron = "0 0 18 * * *")

    @Override
    public Amortization addAmortization(Amortization a) {

        return amortizationRepository.save(a);
    }

    @Override
    public List<Amortization> getAllAmotization() {
        return amortizationRepository.findAll();
    }

}
