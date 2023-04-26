package com.example.pidev.Services;

import com.example.pidev.Entities.*;
import com.example.pidev.Enumerations.status;
import com.example.pidev.Interfaces.IImpayedLoansService;
import com.example.pidev.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImpayedLoansService implements IImpayedLoansService {
    @Autowired
    ImpayedLoansRepository impayedLoansRepository;
    @Autowired
    DetailsLoansRepository detailsLoansRepository;
    @Autowired
    LoanProjectRepository loanproj;
    @Autowired
    ImpayedLoansService impay;
    @Autowired
    AmortizationRepository amortizationRepository;
    @Autowired
    ImplEmailService emailService ;

    @Autowired
    UserRepository userRepository;
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
    //   @Scheduled(cron = "0 0 18 * * *")
    public void SendMailToLateUser () {
               List<ImpayedLoans> impayedLoans = impayedLoansRepository.findAll();

               for (ImpayedLoans impayedLoan : impayedLoans) {
                   Long projectId = impayedLoan.getIProjet();
                   printMessageForImpayedLoans(projectId);
               }
           }
           public void printMessageForImpayedLoans(Long projectId) {

               //LoanProject loan = loanproj.findById(projectId).orElse(null);
               List<ImpayedLoans> impayedLoans = impayedLoansRepository.findByiProjet(projectId);


               long unpaidLoanCount = impayedLoansRepository.countByIProjet(projectId);
               if (impayedLoans == null) {
                   throw new IllegalArgumentException("LoanProject with id " + projectId + " not found");
               }
               ImpayedLoans il = impayedLoans.get(0);
               String to = il.getEmail();
               Integer idp=il.getPaymentNumber();
               String subject = "Unpaid portion of credit!";
               if (unpaidLoanCount < 3) {
                   String text1 = "Dear entrepreneur,\n\n" +
                           "We hope this message finds you well. We wanted to inform you that the portion number "+idp+" of your loan with the ammount of "+il.getAmountNotPayed()+"is not payed. " +
                           "There are " + unpaidLoanCount + " unpaid loans associated with your Loan Project. You have "+(3-unpaidLoanCount)+
                           "remaining chances of not paying your portion otherwise we will go to your insurance and your project will be canceled" +
                           "Please log in to your account to view the details and take appropriate action.\n\n" +
                           "Best regards,\n" +
                           "SalafniMicroFinance team";
                   try {
                       emailService.sendEmail(to, subject, text1);
                   } catch (MessagingException e) {
                       throw new RuntimeException(e);

                   }
               }
               else if (unpaidLoanCount == 3) {
                   String text2 = "Dear entrepreneur,\n\n" +
                           "We hope this message finds you well. We wanted to inform you that the portion number "+idp+" of your loan with the ammount of "+il.getAmountNotPayed()+"is not payed. " +
                           "There are " + unpaidLoanCount + " unpaid loans associated with your Loan Project. You have no remaining chances of not paying your portion otherwise we will go to your insurance and your project will be canceled" +
                           "Please log in to your account to view the details and take appropriate action.\n\n" +
                           "Best regards,\n" +
                           "SalafniMicroFinance team";
                   try {
                       emailService.sendEmail(to, subject, text2);
                   } catch (MessagingException e) {
                       throw new RuntimeException(e);

                   }
               }
               else {
                   String subjectf = " Project Canceled!";
                   String text3 = "Dear entrepreneur,\n\n" +
                           "I regret to inform you that your project [Project Name] has been cancelled. This decision was reached after careful consideration and evaluation of various factors that have affected the feasibility and viability of the project.\n" +
                           "\n" +
                           "I understand that this news may come as a disappointment to you, and I apologize for any inconvenience this may have caused. However, we assure you that we have taken this decision after thorough analysis and keeping in mind the best interest of all parties involved.\n" +
                           "\n" +
                           "We would like to take this opportunity to thank you for your trust in us and for giving us the opportunity to work on your project. We value your association with us and hope that we can continue to collaborate in the future.\n" +
                           "\n" +
                           "Please let us know if you have any questions or concerns regarding the cancellation of the project. We would be happy to discuss this with you further and provide any clarification that you may require.\n" +
                           "\n" +
                           "Thank you for your understanding.\n" +
                           "\n" +
                           "Sincerely,\n" +
                           "\n" +
                           "[Your Name]\n" +
                           "\n" +
                           "SalafniMicroFinance team";
                   try {
                       emailService.sendEmail(to, subjectf, text3);
                   } catch (MessagingException e) {
                       throw new RuntimeException(e);

                   }
               }
           }

        //   @Scheduled(cron = "0 0 18 * * *")
    @Override
    public void transferImpayedLoans() {
        LocalDate now= LocalDate.now();
        Date date = java.sql.Date.valueOf(now);
        List<Amortization> amortizations = amortizationRepository.findAll();
        List<Amortization> m= amortizations.stream().filter(s->existepas(s.getPaymentNumber())&&(s.getPaymentDate().before(date))&&(s.getStatus().equals(status.NOTPAYED))).collect(Collectors.toList());
        System.out.println("nooooo"+m);
        for (Amortization amortization : m) {
            ImpayedLoans impayedLoan = new ImpayedLoans();
            Integer t = amortization.getPaymentNumber();
            impayedLoan.setPaymentNumber(t);
            Long i = amortization.getLoanproject().getIdprojet();
            impayedLoan.setIProjet(i);
            float h =amortization.getPaymentAmount();
            impayedLoan.setAmountNotPayed(h);
            impayedLoan.setInterestRate(10);
            float f= calculateNewAmountToPay(amortization);
            impayedLoan.setNewAmountToPay(f);
            Date d=calculateNewPaymentDate(amortization);
            impayedLoan.setNewPaymentDate(d);
            LoanProject loan = loanproj.findById(i).orElse(null);
            User userMail=userRepository.findByUsername(loan.getOwner());

            impayedLoan.setEmail(userMail.getEmail());

            impayedLoansRepository.save(impayedLoan);
        }

    }
    public boolean existepas(Integer numtrans){
        List<ImpayedLoans> imp = impayedLoansRepository.findByPaymentNumber(numtrans);
        return imp.isEmpty();
    }
    private Float calculateNewAmountToPay(Amortization amortization) {
        Float interestAmount = (amortization.getPaymentAmount() * 10) / 100;
        return amortization.getPaymentAmount() + interestAmount;
    }

    private Date calculateNewPaymentDate(Amortization amortization) {
        Date paydate=amortization.getPaymentDate();
        Calendar cal = Calendar.getInstance();
        cal.setTime(paydate);
        cal.add(Calendar.MONTH, 1);
        Date updatedDate = cal.getTime();
        return updatedDate;
    }


}
