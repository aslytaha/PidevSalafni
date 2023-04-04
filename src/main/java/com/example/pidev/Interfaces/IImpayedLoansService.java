package com.example.pidev.Interfaces;

import com.example.pidev.Entities.ImpayedLoans;

import java.util.List;

public interface IImpayedLoansService {
    public ImpayedLoans addImpayedLoans(ImpayedLoans il);
    public ImpayedLoans updateImpayedLoans(ImpayedLoans il);

    List<ImpayedLoans> getAllImpayedLoans();

    //   Long getImpayedLoanCountForProject(Long Idprojet);
    public void deleteImpayedLoans(Integer ilID);

    void SendMailToLateUser();
    void transferImpayedLoans();
}
