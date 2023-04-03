package com.example.pidev.services;

import com.example.pidev.Entities.Amortization;
import com.example.pidev.Entities.Assurance;

import java.util.List;

public interface IAmortization {
   // void transferImpayedLoans();
    public Amortization addAmortization(Amortization a);
    public List<Amortization> getAllAmotization();
}
