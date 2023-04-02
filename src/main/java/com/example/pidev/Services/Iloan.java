package com.example.pidev.Services;

import com.example.pidev.Entities.DetailsLoans;
import com.example.pidev.Entities.LoanProject;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.List;

@Service
public interface Iloan {


    List<LoanProject> getAllLoanProjects();

    LoanProject getLoanProjectById(Long id);


    LoanProject createLoanProject(Authentication authentication, LoanProject loanProject);

    LoanProject update(LoanProject p);

    void delete(Long Idprojet);









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
    LoanProject updateLoanAmount(Long Idprojet, Float amountborrowed, Principal principal);
}
