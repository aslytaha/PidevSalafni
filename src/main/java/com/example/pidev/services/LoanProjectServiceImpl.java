package com.example.pidev.services;


import com.example.pidev.Entities.Assurance;
import com.example.pidev.Entities.DetailsLoans;
import com.example.pidev.Entities.LoanProject;
import com.example.pidev.Entities.User;
import com.example.pidev.Repositories.AssuranceRepository;
import com.example.pidev.Repositories.DetailsLoansRepository;
import com.example.pidev.Repositories.LoanProjectRepository;
import com.example.pidev.Repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
@NoArgsConstructor
public  class LoanProjectServiceImpl implements Iloan {
    @Autowired
    LoanProjectRepository projectRepository;
    @Autowired
    AssuranceRepository assuranceRepository;
    @Autowired
    DetailsLoansRepository detail;
    @Autowired
    UserRepository userRepository;
   // DetailsLoansServiceImpl detailss;



    @Override
    public List<LoanProject> getAllLoanProjects() {
        return projectRepository.findAll();
    }

    @Override
    public LoanProject getLoanProjectById(Long id) {
        return projectRepository.findById(id).get();
    }
    @Override
    public LoanProject add(LoanProject p) {
        return projectRepository.save(p);
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

  //  @Override
  //  public LoanProject createLoanProject(Authentication authentication, LoanProject loanProject) {
        //User user = userRepository.findByUsername(authentication.getName());
      //  loanProject.setUser(user);
      //  return projectRepository.save(loanProject);


//    @Override
  ///  public LoanProject addDetailsLoan(LoanProject loanProject, DetailsLoans detailsLoans) {
     //   loanProject.setRemainingamount(loanProject.getLoanamount());
       // detailsLoans.setLoanProject(loanProject);
        //loanProject.setDetailsLoans(detailsLoans);
        //projectRepository.save(loanProject);
        //detail.save(detailsLoans);
        //return loanProject;
    //}


    @Override
    public LoanProject addAssuranceToProjectByName(Long Idprojet, String assuranceName) {
        LoanProject project  = projectRepository.findById(Idprojet).get();

        Assurance assurance = assuranceRepository.findByname(assuranceName);

        project.setAssurances(assurance);

        projectRepository.save(project);
         return project;
    }

//    @Override
//    public Map<Long, List<User>> getAllBorrowers() {
  //      List<LoanProject> projects = projectRepository.findAll();
//
  //      Map<Long, List<User>> borrowerMap = new HashMap<>();
//
    //    for (LoanProject project : projects) {
      //      List<LoanProject> loans = project.getDetailsLoans();
//
  //          List<User> borrowers = new ArrayList<>();

    //        for (LoanProject loan : loans) {
      //          User borrower = loan.getBorrower();
        //        borrowers.add(borrower);
 //           }

//            borrowerMap.put(project.getIdprojet(), borrowers);
  //      }

//        return borrowerMap;
  //  }

  //  public List<String> getAllProjectOwners() {
    //    List<LoanProject> projects = projectRepository.findAll();
//
  //      List<String> projectOwners = new ArrayList<>();
//
  //      for (LoanProject project : projects) {
//
  //          String projectOwner = project.getOwner();
    //        projectOwners.add(projectOwner);
//
  //      }
//
  //      return projectOwners;
    //}

    //
    //@Transactional
    //public void deleteProjectAndDetails(Long Idprojet) {
      //  LoanProject project = projectRepository.findById(Idprojet).orElseThrow(() -> new EntityNotFoundException("Project not found with id: " + Idprojet));
        //DetailsLoans detailsss = project.getDetailsLoans();

//        if (detailsss != null) {
  //          detail.delete(detailsss);
    //    }
//
    //    projectRepository.delete(project);
  //  }

//    @Override
//    public int countborrowerByProject( Float amountborrowed) {
//        List<DetailsLoans> listborr = details.countProductByType(amountborrowed);
//        int  countborrowerByProject = listborr.size();
//        return countborrowerByProject;
//    }


 //   public LoanProject updateLoanAmount(Long Idprojet, Float amountborrowed) {
        // Récupération du projet de prêt à partir de l'ID
   //     LoanProject loanProject = projectRepository.findById(Idprojet).orElse(null);

     //   if (loanProject != null) {
            // Récupération du montant total du prêt
       //     Float loanAmount = loanProject.getLoanamount();

            // Mise à jour du montant total du prêt en soustrayant le montant emprunté
         //   loanAmount -= amountborrowed;

            // Mise à jour de l'entité du projet de prêt avec le nouveau montant total du prêt
           // loanProject.setLoanamount(loanAmount);

            // Enregistrement des changements dans la base de données
            //projectRepository.save(loanProject);
        //}

        //return loanProject;
    //}

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