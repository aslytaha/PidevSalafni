package com.example.pidev.services;

import com.example.pidev.Entities.Assurance;
import com.example.pidev.Entities.ImpayedLoans;

import java.util.List;

public interface IImpayedLoansService {
    public ImpayedLoans addImpayedLoans(ImpayedLoans il);
    public ImpayedLoans updateImpayedLoans(ImpayedLoans il);
    public List<ImpayedLoans> getAllImpayedLoans();
    public ImpayedLoans getImpayedLoansById(Integer ilID);
    public void deleteImpayedLoans(Integer ilID);
}
