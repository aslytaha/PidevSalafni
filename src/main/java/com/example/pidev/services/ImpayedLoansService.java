package com.example.pidev.services;

import com.example.pidev.Entities.ImpayedLoans;
import com.example.pidev.Repositories.ImpayedLoansRepository;

import java.util.List;

public class ImpayedLoansService implements IImpayedLoansService {
    ImpayedLoansRepository impayedLoansRepository;
    @Override
    public ImpayedLoans addImpayedLoans(ImpayedLoans il) {

        return impayedLoansRepository.save(il);
    }

    @Override
    public ImpayedLoans updateImpayedLoans(ImpayedLoans il) {

        return impayedLoansRepository.save(il);
    }

    @Override
    public List<ImpayedLoans> getAllImpayedLoans() {

        return impayedLoansRepository.findAll();
    }

    @Override
    public ImpayedLoans getImpayedLoansById(Integer ilID) {

        return impayedLoansRepository.findById(ilID).orElse(null);
    }

    @Override
    public void deleteImpayedLoans(Integer iID) {
        impayedLoansRepository.deleteById(iID);
    }
}
