package com.example.pidev.Services;


import com.example.pidev.Entities.DetailsLoans;
import com.example.pidev.Entities.LoanProject;
import com.example.pidev.Repositories.DetailsLoansRepository;
import com.example.pidev.Repositories.LoanProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public  class LoanProjectServiceImpl implements Iloan {

    LoanProjectRepository projectRepository;
    DetailsLoansRepository detail;
    DetailsLoansServiceImpl detailss;



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

    @Transactional
    public LoanProject updateLoanAmount(Long Idprojet, DetailsLoans details) {
        LoanProject project = projectRepository.findById(Idprojet).orElseThrow(() -> new EntityNotFoundException("Project not found with id: " + Idprojet));

        Float amountBorrowed = details.getAmountborrowed();
        project.setLoanamount(project.getLoanamount() - amountBorrowed); // Mettre à jour la valeur de loanAmount

        projectRepository.save(project);
        detail.save(details);


        return project;
    }


}






