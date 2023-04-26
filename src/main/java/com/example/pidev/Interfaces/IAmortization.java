package com.example.pidev.Interfaces;

import com.example.pidev.Entities.Amortization;
import com.example.pidev.Entities.LoanProject;

import java.util.List;

public interface IAmortization {
    List<Amortization> saveAll(List<Amortization> amortizationList);

    //    public List<Amortization> generateConstantAmortizationTable(LocalDate startDate, LocalDate finishDate, Float loanAmount, type paymentType) {
    //        List<Amortization> amortizationTable = new ArrayList<>();
    //        int numPaymentsPerYear = 12; // by default, assume monthly payments
    //        if (paymentType == type.QUARTERLY) {
    //            numPaymentsPerYear = 4;
    //        }
    //        float interestRate = 0.15f; // assume fixed interest rate of 15%
    //        int numPayments = calculateNumPayments(startDate, finishDate, numPaymentsPerYear, paymentType);
    //        float monthlyInterestRate = interestRate / numPaymentsPerYear;
    //        float monthlyPayment = calculateMonthlyPayment(loanAmount, monthlyInterestRate, numPayments);
    //
    //        float remainingBalance = loanAmount;
    //        LocalDate currentDate = startDate;
    //        for (int i = 0; i < numPayments; i++) {
    //            float interest = remainingBalance * monthlyInterestRate;
    //            float principal = monthlyPayment - interest;
    //            if (remainingBalance - principal < 0) {
    //                principal = remainingBalance;
    //                monthlyPayment = principal + interest;
    //            }
    //            remainingBalance -= principal;
    //
    //            LocalDate paymentDat = calculatePaymentDate(startDate, i, paymentType);
    //            Amortization amortization = new Amortization();
    //            amortization.setPaymentNumber(i + 1);
    //            amortization.setPaymentDate(java.sql.Date.valueOf(paymentDat));
    //
    //            amortization.setInterest(interest);
    //            amortization.setPrincipal(principal);
    //            amortization.setRemainingAmount(remainingBalance);
    //            amortizationTable.add(amortization);
    //            currentDate = calculateNextPaymentDate(currentDate, paymentType);
    //        }
    //
    //        return amortizationTable;
    //    }
    List<Amortization> generateAmortizationTable(LoanProject loanproject);
}