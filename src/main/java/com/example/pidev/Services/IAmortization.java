package com.example.pidev.Services;

import com.example.pidev.Entities.Amortization;
import com.example.pidev.Entities.LoanProject;
import com.example.pidev.Entities.type;

import java.util.List;

public interface IAmortization {






    //    public List<Amortization> generateAmortizationTable(Long Idprojet) {
    //        LoanProject loanP = loan.findById(Idprojet).orElse(null);
    //
    //       Float  loanAmount = loanP.getLoanamount();
    //        type paymentType = loanP.getPaymenttype();
    //        int   numberOfPeriods = paymentType == type.MONTHLY ? 12 : 4;
    //
    //        // Call generateAmortizationTable method to generate the amortization table
    //        List<Amortization> amortizationTable = generateAmortization(loanAmount, numberOfPeriods);
    //
    //        return amortizationTable;
    //    }


    List<Amortization> generateAmortizationT(Float loanAmount, LoanProject loan);

    List<Amortization> generateAmortizationTable(Float loanAmount, type paymentType, int numberOfPeriods);
}
