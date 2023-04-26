package com.example.pidev.Services;


import com.example.pidev.Entities.DetailsLoans;
import com.example.pidev.Entities.LoanProject;
import com.example.pidev.Entities.User;
import com.example.pidev.Enumerations.type;
import com.example.pidev.Interfaces.Iloan;
import com.example.pidev.Repositories.DetailsLoansRepository;
import com.example.pidev.Repositories.LoanProjectRepository;
import com.example.pidev.Repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.*;

@Service
@AllArgsConstructor
@NoArgsConstructor
public  class LoanProjectServiceImpl implements Iloan {

    @Autowired
    LoanProjectRepository projectRepository;
    @Autowired
    DetailsLoansRepository detail;
    @Autowired
    DetailsLoansServiceImpl detailss;
    @Autowired
    UserRepository userop;

    @Autowired
    ImplEmailService emailService;


    @Override
    public List<LoanProject> getAllLoanProjects() {
        return projectRepository.findAll();
    }

//    @Override
//    public LoanProject getLoanProjectById(Long id) {
//        return projectRepository.findByIdprojet(id);
//    }


public boolean isLoanProjectValid(LoanProject project) {
    boolean isValid = false;

    // Check if loan amount is between 5000 and 50000 and user has no existing loan projects
    if ((project.getLoanamount() > 5000 ) && (project.getLoanamount() < 50000)){
        return   true;
    }else

        return isValid;
}

    @Override
    public LoanProject createLoanProject(Authentication authentication, LoanProject loanProject) {
        User user = userop.findByUsername(authentication.getName());
        loanProject.setUser(user);
        loanProject.setOwner(user.getUsername());
        loanProject.setValidate(false);
        // set payment type from user input
        String paymentTypeStr = loanProject.getPaymenttype().toString().toUpperCase();
        type paymentType = type.valueOf(paymentTypeStr);
        loanProject.setPaymenttype(paymentType);

        // set refund period based on payment type
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(loanProject.getStartdate());

        if (paymentType == type.MONTHLY) {
            calendar.add(Calendar.MONTH, 1);
        } else if (paymentType == type.QUARTERLY) {
            calendar.add(Calendar.MONTH, 3);
        }

        loanProject.setRefundperiod(calendar.getTime());

        LoanProject savedProject = projectRepository.save(loanProject);
        if (isLoanProjectValid(savedProject)==true) {
            System.out.println("ttttttttttttttttttttttttt");
            savedProject.setValidate(true);
            update(savedProject);
        }

        return savedProject;
    }


    @Override
    public LoanProject update(LoanProject p) {
        LoanProject project = projectRepository.findById(p.getIdprojet()).orElse(null);
        if (project != null) {
            project.setValidate(p.getValidate());
            return projectRepository.save(project);
        } else {
            return null;
        }
    }


    @Override
    public void delete(Long Idprojet) {
//        projectRepository.findById;
        projectRepository.deleteById(Idprojet);

    }


    //
    @Transactional
    public void deleteProjectAndDetails(Long Idprojet) {
        LoanProject project = projectRepository.findById(Idprojet).orElseThrow(() -> new EntityNotFoundException("Project not found with id: " + Idprojet));

        List<DetailsLoans> detailsLoansSet=detail.findAll();
        for(DetailsLoans detailsLoans: detailsLoansSet)
        {if (detailsLoans.getLoanDate().equals(project))
           {
               detail.delete(detailsLoans);
           }
        }


        projectRepository.delete(project);
    }

//    @Override
//    public int countborrowerByProject( Float amountborrowed) {
//        List<DetailsLoans> listborr = details.countProductByType(amountborrowed);
//        int  countborrowerByProject = listborr.size();
//        return countborrowerByProject;
//    }


    //        public LoanProject updateLoanAmount(Long Idprojet, Float amountborrowed) {
//            // Récupération du projet de prêt à partir de l'ID
//            LoanProject loanProject = projectRepository.findById(Idprojet).orElse(null);
//
//            if (loanProject != null) {
//                // Récupération du montant total du prêt
//                Float loanAmount = loanProject.getLoanamount();
//
//                // Mise à jour du montant total du prêt en soustrayant le montant emprunté
//                loanAmount -= amountborrowed;
//
//                // Mise à jour de l'entité du projet de prêt avec le nouveau montant total du prêt
//                loanProject.setLoanamount(loanAmount);
//
//                // Enregistrement des changements dans la base de données
//                projectRepository.save(loanProject);
//            }
//
//            return loanProject;
//        }
//    @Override
//    public LoanProject addDetailsLoan(LoanProject loanProject, DetailsLoans detailsLoans) {
//        loanProject.setDetailsLoans(detailsLoans);
////        loanProject.setDetailsLoans.(detailsLoans.setRemainingamount(loanProject.getLoanamount()));
//        loanProject.setDetailsLoans(detailsLoans.setRemainingamount(loanProject.getLoanamount()));
//        detailsLoans.setLoanProject(loanProject);
//        projectRepository.save(loanProject);
//        detail.save(detailsLoans);
//        return loanProject;
//    }
//@Override
//public LoanProject updateLoanAmount(Long Idprojet, Float amountborrowed, Principal principal) {
//    // Récupération du projet de prêt à partir de l'ID
//    LoanProject loanP = projectRepository.findById(Idprojet).orElse(null);
//    Float remainingamount=loanP.getRemainingamount();
//
//        if (loanP != null) {
//            // Mise à jour du montant total du prêt en soustrayant le montant emprunté
//            remainingamount-=amountborrowed;
//            // Récupération de l'utilisateur connecté
//            // Création d'une nouvelle entité DetailsLoans
//            DetailsLoans newDetailsLoan = new DetailsLoans();
//            newDetailsLoan.setAmountborrowed(amountborrowed);
//            newDetailsLoan.setBorrowedName(principal.getName());
//        // Enregistrement du nouveau DetailsLoans dans la base de données
//        detail.save(newDetailsLoan);
//
//        // Mise à jour de l'entité du projet de prêt avec le nouveau DetailsLoans
//        loanP.setDetailsLoans(newDetailsLoan);
//
//        // Mise à jour de l'entité du projet de prêt avec le nouveau montant total du prêt
//        loanP.setRemainingamount(remainingamount);
//
//            // Envoi d'un email à l'utilisateur qui a créé le projet de prêt
//            String to = loanP.getUser().getEmail();
//            String subject = "Nouveau dépôt sur le projet de prêt #" + loanP.getLoanamount();
//            String body = "Un montant de " + remainingamount + " a été déposé sur le projet de prêt #" + loanP.getLoanamount()
//                    + " par " + principal.getName();
//            Email email = new Email();
//            email.setTo(to);
//            email.setSubject(subject);
//            email.setBody(body);
//            try {
//                service.sendEmail(email);
//            } catch (MessagingException e) {
//                // Handle the exception
//                e.printStackTrace();
//            }
//
//        // Enregistrement des changements dans la base de données
//        projectRepository.save(loanP);
//    }
//
//    return loanP;
//}

    public LoanProject updateLoanAmount (Long Idprojet, Float amountborrowed, Authentication authentication){
        // Récupération du projet de prêt à partir de l'ID
        LoanProject loanP = projectRepository.findById(Idprojet).get();


        Float remainingamount = loanP.getRemainingamount();

        // Mise à jour du montant total du prêt en soustrayant le montant emprunté
        remainingamount -= amountborrowed;


        DetailsLoans newDetailsLoan = new DetailsLoans();

        newDetailsLoan.setAmountborrowed(amountborrowed);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        newDetailsLoan.setBorrowedName(userDetails.getUsername());
//        newDetailsLoan.setLoanDate(new Date());
        // Enregistrement du nouveau DetailsLoans dans la base de données
        detail.save(newDetailsLoan);

        // Mise à jour de l'entité du projet de prêt avec le nouveau DetailsLoans
        newDetailsLoan.setLoanProject(loanP);

        // Mise à jour de l'entité du projet de prêt avec le nouveau montant total du prêt
        loanP.setRemainingamount(remainingamount);


        // Enregistrement des changements dans la base de données
        projectRepository.save(loanP);

        User user = userop.findByUsername(loanP.getOwner());
        String to = user.getEmail();


        String Subject = "Loan Project updated !";
        String text = "Dear " + user.getUsername() + ",\n\n"
                + "The loan project '" + loanP.getProjectname() + "' has been updated with a new amount borrowed of " + amountborrowed + " by " + userDetails.getUsername() + ".\n\n";
        System.out.println("text" + text);

        try {
            emailService.SendResetMail(to,Subject,text);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        return loanP;

    }





}



















