package com.example.pidev.Controllers;

import com.example.pidev.Entities.DetailsLoans;
import com.example.pidev.Entities.User;
import com.example.pidev.Repositories.LoanProjectRepository;
import com.example.pidev.Repositories.UserRepository;
import com.example.pidev.Services.DetailsLoansServiceImpl;
import com.example.pidev.Services.Iloan;
import com.example.pidev.Services.LoanProjectServiceImpl;
import com.example.pidev.Entities.LoanProject;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.security.Principal;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Project")
public class LoanProjectController {


    @Autowired
    LoanProjectServiceImpl loanProject;
    LoanProjectRepository loan;
    UserRepository userpo;

    @GetMapping("/getProjects")
    public List<LoanProject> getProjects() {
        List<LoanProject> loanlist = loanProject.getAllLoanProjects();
        return loanlist;
    }

    @GetMapping("/retrieve/{id}")
        public LoanProject getLoanProjectById (@PathVariable Long id){
        return loanProject.getLoanProjectById(id);
        }
//    @PostMapping("/add-project")
//    public LoanProject add(@RequestBody LoanProject p) {
//        p.setValidate(false);////validate par defaut false dés lajout
//        p.setRemainingamount(p.getLoanamount());
//        LoanProject loanproject = loanProject.add(p);
//        return  loan.save(loanproject);
//    }


    @PostMapping("/add-project")
    public LoanProject add(@RequestBody LoanProject p, Principal principal) {
        p.setValidate(false); //validate par defaut false dès l'ajout
        p.setRemainingamount(p.getLoanamount());
        LoanProject loanproject = loanProject.createLoanProject((Authentication) principal, p);
        return loan.save(loanproject);
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

//    @GetMapping("/retrieve-complaintByClient/{complaint-id}")
//    @ResponseBody
//    public LoanProject retrieveLoanByDetail(@PathVariable("Idprojet") Long Idprojet){
//        return loanProject.retrieveLoanByDetail(Idprojet);
//    }

    @PostMapping("/projects/delete/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable Long id) {
        try {
            loanProject.deleteProjectAndDetails(id);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/{projectId}/borrow")
    public ResponseEntity<LoanProject> borrow(@PathVariable Long projectId, @RequestParam Float amount) {
        LoanProject loanproject = loanProject.updateLoanAmount(projectId, amount);
        if (loanproject != null) {
            return ResponseEntity.ok(loanproject);
        } else {
            return ResponseEntity.notFound().build();
        }
    }





@ExceptionHandler(Exception.class)
public ResponseEntity<?> handleException(Exception e) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error: " + e.getMessage());
}






}

