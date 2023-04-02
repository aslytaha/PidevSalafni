package com.example.pidev.Services;


import com.example.pidev.Entities.*;
import com.example.pidev.Repositories.DetailsLoansRepository;
import com.example.pidev.Repositories.LoanProjectRepository;
import com.example.pidev.Repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.security.Principal;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
    EmailService service;



    @Override
    public List<LoanProject> getAllLoanProjects() {
        return projectRepository.findAll();
    }

    @Override
    public LoanProject getLoanProjectById(Long id) {
        return projectRepository.findById(id).get();
    }

//    @Override
//    public LoanProject createLoanProject(Authentication authentication, LoanProject loanProject) {
//        User user = userop.findByUsername(authentication.getName());
//        loanProject.setUser(user);
//
//        // set payment type from user input
//        String paymentTypeStr = loanProject.getPaymenttype().toString().toUpperCase();
//        type paymentType = type.valueOf(paymentTypeStr);
//        loanProject.setPaymenttype(paymentType);
//        return projectRepository.save(loanProject);
//    }


    @Override
    public LoanProject createLoanProject(Authentication authentication, LoanProject loanProject) {
        User user = userop.findByUsername(authentication.getName());
        loanProject.setUser(user);

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

        return projectRepository.save(loanProject);
    }



    @Override
    @Transactional
    public LoanProject update(LoanProject p) {
        return projectRepository.save(p);
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
    DetailsLoans detailsss = project.getDetailsLoans();

    if (detailsss != null) {
        detail.delete(detailsss);
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
@Override
public LoanProject updateLoanAmount(Long Idprojet, Float amountborrowed, Principal principal) {
    // Récupération du projet de prêt à partir de l'ID
    LoanProject loanP = projectRepository.findById(Idprojet).orElse(null);
    Float remainingamount=loanP.getRemainingamount();

        if (loanP != null) {
            // Mise à jour du montant total du prêt en soustrayant le montant emprunté
            remainingamount-=amountborrowed;
            // Récupération de l'utilisateur connecté
            // Création d'une nouvelle entité DetailsLoans
            DetailsLoans newDetailsLoan = new DetailsLoans();
            newDetailsLoan.setAmountborrowed(amountborrowed);
            newDetailsLoan.setBorrowedName(principal.getName());
        // Enregistrement du nouveau DetailsLoans dans la base de données
        detail.save(newDetailsLoan);

        // Mise à jour de l'entité du projet de prêt avec le nouveau DetailsLoans
        loanP.setDetailsLoans(newDetailsLoan);

        // Mise à jour de l'entité du projet de prêt avec le nouveau montant total du prêt
        loanP.setRemainingamount(remainingamount);

            // Envoi d'un email à l'utilisateur qui a créé le projet de prêt
            String to = loanP.getUser().getEmail();
            String subject = "Nouveau dépôt sur le projet de prêt #" + loanP.getLoanamount();
            String body = "Un montant de " + remainingamount + " a été déposé sur le projet de prêt #" + loanP.getLoanamount()
                    + " par " + principal.getName();
            Email email = new Email();
            email.setTo(to);
            email.setSubject(subject);
            email.setBody(body);
            try {
                service.sendEmail(email);
            } catch (MessagingException e) {
                // Handle the exception
                e.printStackTrace();
            }

        // Enregistrement des changements dans la base de données
        projectRepository.save(loanP);
    }

    return loanP;
}


















































//    @Transactional
//    public LoanProject updateLoanAmount(Long Id, DetailsLoans details) {
//        LoanProject project = projectRepository.findById(Id).orElseThrow(() -> new EntityNotFoundException("Project not found with id: " + Id));
//
//        Float amountborrowed = details.getAmountborrowed();
//        project.setLoanamount(project.getLoanamount() - amountborrowed); // Mettre à jour la valeur de loanAmount
//
//        projectRepository.save(project);
//        detail.save(details);
//
//
//        return project;
//    }

//    @Transactional
//    public LoanProject updateLoanAmount(Long Id, Float amountborrowed) {
//        LoanProject project = projectRepository.findById(Id).orElseThrow(() -> new EntityNotFoundException("Project not found with id: " + Id));
//
//        // Mettre à jour la valeur de loanAmount
//        project.setLoanamount(project.getLoanamount() - amountborrowed);
//
//        // Créer un objet DetailsLoans avec les détails de prêt et lier à LoanProject
//        DetailsLoans details = new DetailsLoans();
//        details.setAmountborrowed(amountborrowed);
//        details.setLoanDate(new Date());
//        details.setLoanProject(project);
//
//        // Sauvegarder les modifications dans la base de données
//        projectRepository.save(project);
//        detail.save(details);
//
//        return project;
//    }


//////3eme esaaai

//    @Transactional
//    public LoanProject updateLoanAmount(Long id, DetailsLoans details) {
//        LoanProject project = projectRepository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Project not found with id: " + id));
//
//        float amountBorrowed = details.getAmountborrowed();
//        float remainingAmount = project.getLoanamount() - amountBorrowed;
//
//        if (remainingAmount < 0) {
//            throw new IllegalArgumentException("Amount borrowed cannot exceed loan amount");
//        }
//
//        project.setLoanamount(remainingAmount);
//        details.setLoanProject(project);
//        detail.save(details);
//
//        return project;
//    }

}






