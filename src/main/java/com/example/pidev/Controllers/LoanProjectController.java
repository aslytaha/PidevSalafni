package com.example.pidev.Controllers;

import com.example.pidev.Entities.Amortization;
import com.example.pidev.Entities.LoanProject;
import com.example.pidev.Repositories.LoanProjectRepository;
import com.example.pidev.Repositories.UserRepository;
import com.example.pidev.Services.AmotizationService;
import com.example.pidev.Services.LoanProjectServiceImpl;
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
    @Autowired
    LoanProjectRepository loan;
    @Autowired
    UserRepository userpo;
    @Autowired
    AmotizationService amor;

    @GetMapping("/getProjects")
    public List<LoanProject> getProjects() {
        List<LoanProject> loanlist = loanProject.getAllLoanProjects();
        return loanlist;
    }
//    @GetMapping("/getLoanProject/{id}")
//    public LoanProject getLoanProjectById(@PathVariable("id") Long id) {
//        LoanProject loanproject = loanProject.getLoanProjectById(id);
//        List<Amortization> amortizationTable = amor.generateAmortizationTable(loanproject.getLoanamount(), loanproject);
//        loanproject.setAmortizationTable(amortizationTable); // set the amortization table in the loan project
//        return loanproject;
//    }


//    @GetMapping("/retrieve/{id}")
//    public LoanProject getLoanProjectByiddd(@PathVariable Long id) {
//        return loanProject.getLoanProjectById(id);
//    }


    //    @PostMapping("/add-project")
//    public LoanProject add(@RequestBody LoanProject p, Principal principal) {
//        p.setValidate(false); //validate par defaut false dès l'ajout
//        p.setRemainingamount(p.getLoanamount());
//        LoanProject loanproject = loanProject.createLoanProject((Authentication) principal, p);
//        return loan.save(loanproject);
//    }
//@PostMapping("/add-project")
//public LoanProject add(@RequestBody LoanProject p, Principal principal) {
//    p.setValidate(false); //validate par defaut false dès l'ajout
//    p.setRemainingamount(p.getLoanamount());
//    LoanProject loanproject = loanProject.createLoanProject((Authentication) principal, p);
//    return loan.save(loanproject);
//}
    @PostMapping("/add-project")
    public LoanProject add(@RequestBody LoanProject p, Principal principal) {
//    p.setValidate(false); // validate par defaut false dès l'ajout
        p.setRemainingamount(p.getLoanamount());
        LoanProject loanproject = loanProject.createLoanProject((Authentication) principal, p);


        List<Amortization> amortizationTable = amor.generateAmortizationTable(loanproject);

// Set the LoanProject object for each Amortization object in the list
        amortizationTable.forEach(a -> a.setLoanproject(loanproject));

// Save the amortization table for the loan project
        amor.saveAll(amortizationTable);
        LoanProject lal = loan.save(loanproject);
        // Validate the loan project
        if (loanProject.isLoanProjectValid(lal)) {
            lal.setValidate(true);
        }
        return lal;

    }


    @DeleteMapping("/remove/{Idprojet}")
    public void remove(@PathVariable("Idprojet") Long Idprojet) {
        loanProject.delete(Idprojet);
    }

    @PutMapping("/update")
    public LoanProject update(@RequestBody LoanProject p) {
        LoanProject loanproject = loanProject.update(p);
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
    public ResponseEntity<LoanProject> borrow(@PathVariable Long projectId, @RequestParam Float amount, Authentication authentication) {
        LoanProject loanproject = loanProject.updateLoanAmount(projectId, amount, authentication);
        if (loanproject != null) {
            return ResponseEntity.ok(loanproject);
        } else {
            return ResponseEntity.notFound().build();
        }

    }
}







//    @ExceptionHandler(Exception.class)
//public ResponseEntity<?> handleException(Exception e) {
//    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("aziza Server Error: " + e.getMessage());
//}








