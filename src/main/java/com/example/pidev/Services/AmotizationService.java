package com.example.pidev.Services;


import com.example.pidev.Entities.Amortization;
import com.example.pidev.Entities.LoanProject;
import com.example.pidev.Entities.type;
import com.example.pidev.Repositories.AmortizationRepository;
import com.example.pidev.Repositories.LoanProjectRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class AmotizationService implements IAmortization{

    @Autowired
    LoanProjectRepository loan;
    LoanProjectServiceImpl loanll;
    @Autowired
    AmortizationRepository repor;

    @Override
    public List<Amortization> generateAmortizationT(Float loanAmount, LoanProject loan) {
        Long projectId = loan.getIdprojet(); // get the ID of the LoanProject object
        type paymentType = loan.getPaymenttype();
        int numberOfPeriods = paymentType == type.MONTHLY ? 12 : 4;

        // Call generateAmortizationTable method to generate the amortization table
        List<Amortization> amortizationTable = generateAmortizationTable(loanAmount, paymentType, numberOfPeriods);

        // Set the LoanProject object for each Amortization object in the list
        amortizationTable.forEach(a -> a.setLoanproject(loan));

        return amortizationTable;
    }

//    private int getNumberOfPeriods(Date startDate, Date finishDate, type paymentType) {
//        int numberOfPeriods;
//        if (paymentType == type.MONTHLY) {
//            numberOfPeriods = (int) ChronoUnit.MONTHS.between(startDate.toInstant(), finishDate.toInstant()) + 1;
//        } else {
//            numberOfPeriods = (int) ChronoUnit.MONTHS.between(startDate.toInstant(), finishDate.toInstant()) / 3 + 1;
//        }
//        return numberOfPeriods;
//    }

    public List<Amortization> saveAll(List<Amortization> amortizationList) {
        return repor.saveAll(amortizationList);
    }

    @Override
    public List<Amortization> generateAmortizationTable(Float loanAmount, type paymentType, int numberOfPeriods) {
        List<Amortization> amortizationTable = new ArrayList<>();
        int numPaymentsPerYear = 12; // by default, assume monthly payments
        if (paymentType == type.QUARTERLY) {
            numPaymentsPerYear = 4;
        }
        int numPayments = numPaymentsPerYear * 1; // amortization table will be for 1 year

        float interestRate = 0.15f; // assume fixed interest rate of 15%
        float monthlyInterestRate = interestRate / numPaymentsPerYear;
        float monthlyPayment = loanAmount * (monthlyInterestRate / (1 - (float) Math.pow(1 + monthlyInterestRate, -numPayments)));

        float remainingBalance = loanAmount;
        for (int i = 0; i < numPayments; i++) {
            float interest = remainingBalance * monthlyInterestRate;
            float principal = monthlyPayment - interest;
            if (remainingBalance - principal < 0) {
                principal = remainingBalance;
                monthlyPayment = principal + interest;
            }
            remainingBalance -= principal;

            Amortization amortization = new Amortization();
            amortization.setPaymentNumber(i + 1);
            amortization.setInterest(interest);
            amortization.setPrincipal(principal);
            amortization.setRemainingAmount(remainingBalance);
            amortizationTable.add(amortization);
        }

        return amortizationTable;
    }




}
