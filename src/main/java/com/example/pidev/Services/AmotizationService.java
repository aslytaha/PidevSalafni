package com.example.pidev.Services;


import com.example.pidev.Entities.Amortization;
import com.example.pidev.Entities.LoanProject;
import com.example.pidev.Enumerations.type;
import com.example.pidev.Interfaces.IAmortization;
import com.example.pidev.Repositories.AmortizationRepository;
import com.example.pidev.Repositories.LoanProjectRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class AmotizationService implements IAmortization {

    @Autowired
    LoanProjectRepository loan;
    LoanProjectServiceImpl loanll;
    @Autowired
    AmortizationRepository repor;


@Override
    public List<Amortization> saveAll(List<Amortization> amortizationList) {
        return repor.saveAll(amortizationList);
    }
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
    @Override
public List<Amortization> generateAmortizationTable(LoanProject loanproject) {
    Float loanAmount = loanproject.getLoanamount();
    type paymentType = loanproject.getPaymenttype();
    Date start = loanproject.getStartdate();
    LocalDate startDate = start.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

    Date finishDate = loanproject.getFinishdate();
    LocalDate localFinishDate = finishDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
     ////création d'une liste et sera remplie par les paiements d'amortiss
    List<Amortization> amortizationTable = new ArrayList<>();
    int numPaymentsPerYear = 12; // by default, assume monthly payments
    if (paymentType == type.QUARTERLY) {
        numPaymentsPerYear = 4;
    }
    float interestRate = 0.15f; // assume fixed interest rate of 15%
        //cette methode calcule le nb total de paiem pendant la durée du pret
    int numPayments = calculateNumPayments(startDate, localFinishDate, numPaymentsPerYear, paymentType);
    float monthlyInterestRate = interestRate / numPaymentsPerYear;
    //calcul du paiement mensuel
    float monthlyPayment = calculateMonthlyPayment(loanAmount, monthlyInterestRate, numPayments);

    float remainingBalance = loanAmount;
    LocalDate currentDate = startDate;
    for (int i = 0; i < numPayments; i++) {
        // calcule le montant d'intérêt à payer pour le paiement d'amortissement actuel.
        float interest = remainingBalance * monthlyInterestRate;
        //calcule le montant principal à payer pour le paiement d'amortissement actuel.
        float principal = monthlyPayment - interest;
       //vérifie si le montant principal calculé dépasse le montant restant
       // vérifie si le solde restant moins le principal est inférieur à zéro. Si c'est le cas, le principal est réduit pour correspondre au solde restant et le paiement mensuel est recalculé en ajoutant les intérêts. Cela permet d'éviter les paiements excessifs lorsque le solde restant est inférieur au paiement mensuel.
        if (remainingBalance - principal < 0) {
            principal = remainingBalance;
            monthlyPayment = principal + interest;
        }
        remainingBalance -= principal;

        LocalDate paymentDate = calculatePaymentDate(startDate, i, paymentType);
        Amortization amortization = new Amortization();
        amortization.setPaymentNumber(numPayments - i);
        amortization.setPaymentDate(Date.from(paymentDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        amortization.setInterest(interest);
        amortization.setPrincipal(principal);
        amortization.setRemainingAmount(remainingBalance);
        amortization.setLoanproject(loanproject);
        amortizationTable.add(amortization);
        currentDate = calculateNextPaymentDate(currentDate, paymentType);
    }
//        Collections.sort(amortizationTable, new Comparator<Amortization>() {
//            @Override
//            public int compare(Amortization a1, Amortization a2) {
//                return Integer.compare(a1.getPaymentNumber(), a2.getPaymentNumber());
//            }
//        });
    return amortizationTable;
}

    private int calculateNumPayments(LocalDate startDate, LocalDate finishDate, int numPaymentsPerYear, type paymentType) {
        if (paymentType == type.MONTHLY) {
            return (int) ChronoUnit.MONTHS.between(startDate, finishDate);
        } else { // PaymentType.QUARTERLY
            return (int) ChronoUnit.MONTHS.between(startDate, finishDate) / 3;
        }
    }

    private float calculateMonthlyPayment(Float loanAmount, float monthlyInterestRate, int numPayments) {
        return loanAmount * (monthlyInterestRate / (1 - (float) Math.pow(1 + monthlyInterestRate, -numPayments)));
    }

    private LocalDate calculatePaymentDate(LocalDate startDate, int paymentNumber, type paymentType) {
        if (paymentType == type.MONTHLY) {
            return startDate.plusMonths(paymentNumber);
        } else { // PaymentType.QUARTERLY
            return startDate.plusMonths(paymentNumber * 3);
        }
    }

    private LocalDate calculateNextPaymentDate(LocalDate currentDate, type paymentType) {
        if (paymentType == type.MONTHLY) {
            return currentDate.plusMonths(1);
        } else { // PaymentType.QUARTERLY
            return currentDate.plusMonths(3);
        }
    }




}
