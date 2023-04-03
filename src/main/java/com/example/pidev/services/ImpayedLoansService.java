package com.example.pidev.services;

import com.example.pidev.Entities.Amortization;
import com.example.pidev.Entities.ImpayedLoans;
import com.example.pidev.Entities.LoanProject;
import com.example.pidev.Repositories.AmortizationRepository;
import com.example.pidev.Repositories.DetailsLoansRepository;
import com.example.pidev.Repositories.ImpayedLoansRepository;
import com.example.pidev.Repositories.LoanProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
@Service
public class ImpayedLoansService implements IImpayedLoansService {
    @Autowired
    ImpayedLoansRepository impayedLoansRepository;
    @Autowired
    DetailsLoansRepository detailsLoansRepository;
    @Autowired
    LoanProjectRepository loanproj;
    @Autowired
    EmailService service;
    @Autowired
    ImpayedLoansService impay;
    @Autowired
    AmortizationRepository amortizationRepository;
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

 //   @Override
   // public List<ImpayedLoans> getImpayedLoansByClientId(Long clientId) {
     //   return detailsLoansRepository.findImpayedLoansByClientId(clientId);
    //}

    @Override
    public void deleteImpayedLoans(Integer iID) {
        impayedLoansRepository.deleteById(iID);
    }

 //   public void printMessageForImpayedLoans(Long projectId) {
    //    long unpaidLoanCount = impayedLoansRepository.countByIProjet(projectId);

       // if (unpaidLoanCount < 3) {

           // try {
           //     service.sendEmail(to, Subject, text);
           // } catch (MessagingException e) {
            //    throw new RuntimeException(e);
       //     }
     //   }
           public void SendMailToLateUser () {
               List<ImpayedLoans> impayedLoans = impayedLoansRepository.findAll();

               for (ImpayedLoans impayedLoan : impayedLoans) {
                   Long projectId = impayedLoan.getIProjet();
                   printMessageForImpayedLoans(projectId);
               }
           }
           public void printMessageForImpayedLoans(Long projectId) {

               LoanProject loan = loanproj.findById(projectId).orElse(null);


               long unpaidLoanCount = impayedLoansRepository.countByIProjet(projectId);
               if (loan == null) {
                   throw new IllegalArgumentException("LoanProject with id " + projectId + " not found");
               }
               if (unpaidLoanCount < 3) {
                   String to = loan.getEmail();
                   String subject = "Loan Project updated!";
                   String text = "Dear " + loan.getOwner() + ",\n\n" +
                           "We hope this message finds you well. We wanted to inform you that there are " + unpaidLoanCount + " unpaid loans associated with your Loan Project. Please log in to your account to view the details and take appropriate action.\n\n" +
                           "Best regards,\n" +
                           "Your Loan Project team";
                   try {
                       service.sendEmail(to, subject, text);
                   } catch (MessagingException e) {
                       throw new RuntimeException(e);

                   }
               }
           }


    @Override
    public void transferImpayedLoans() {
        //LocalDate now = LocalDate.now();
        //List<Amortization> impayedLoans = amortizationRepository.findImpayedLoans(now);
        List<Amortization> impayedLoans = amortizationRepository.findAll();

        for (Amortization amortization : impayedLoans) {
            ImpayedLoans impayedLoan = new ImpayedLoans();
            Integer t = amortization.getNumDetails();
            impayedLoan.setNumTrans(t);
            Long i = amortization.getLoanproject().getIdprojet();
            impayedLoan.setIProjet(i);
            float h =amortization.getPaymentAmount();
            impayedLoan.setAmountNotPayed(h);
            impayedLoan.setInterestRate(10);
            float f= calculateNewAmountToPay(amortization);
            impayedLoan.setNewAmountToPay(f);
            Date d=calculateNewPaymentDate(amortization);
            impayedLoan.setNewPaymentDate(d);
           // LoanProject loan = loanproj.findById(i).orElse(null);
            //String mail=loan.getEmail();
            //impayedLoan.setEmail(mail);

            impayedLoansRepository.save(impayedLoan);
        }

    }
    private Float calculateNewAmountToPay(Amortization amortization) {
        Float interestAmount = (amortization.getPaymentAmount() * 10) / 100;
        return amortization.getPaymentAmount() + interestAmount;
    }

    private Date calculateNewPaymentDate(Amortization amortization) {

        return amortization.getPaymentDate();
    }


}
