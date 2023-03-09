package com.example.pidev.Controllers;

import com.example.pidev.Entities.DetailsLoans;
import com.example.pidev.Services.DetailsLoansServiceImpl;
import com.example.pidev.Services.Iloan;
import com.example.pidev.Services.LoanProjectServiceImpl;
import com.example.pidev.Entities.LoanProject;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Project")
public class LoanProjectController {

    LoanProjectServiceImpl loanProject;
    LoanProject loan;
    DetailsLoans detaislloan;

    @GetMapping("/getProjects")
    public List<LoanProject> getProjects() {
        List<LoanProject> loanlist = loanProject.getAllLoanProjects();
        return loanlist;
    }

    @GetMapping("/retrieve/{id}")
        public LoanProject getLoanProjectById (@PathVariable Long id){
        return loanProject.getLoanProjectById(id);
        }
    @PostMapping("/add-project")
    public LoanProject add(@RequestBody LoanProject p) {
        p.setValidate(false);   ////validate par defaut false dés lajout
        LoanProject loanproject = loanProject.add(p);
        return loanproject;
    }

    @DeleteMapping("/remove/{Idprojet}")
    public void remove(@PathVariable("Idprojet") Long Idprojet) {
        loanProject.delete(Idprojet);
    }

    @PutMapping("/update")
    public LoanProject update(@RequestBody LoanProject p) {
        LoanProject loanproject= loanProject.update(p);
        return loanproject;
    }

//    public void Nbborrowers(double amount) {
//        loan.getLoanamount().+= amount;
//    }
//
//    public boolean objectifAtteint() {
//        return (loanamount == amountborrowed);
//    }
//    public double getAmountBorrowed() {
//        return detaislloan.getAmountborrowed();
//    }

}

