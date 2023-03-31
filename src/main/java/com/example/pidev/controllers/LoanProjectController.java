package com.example.pidev.controllers;

import com.example.pidev.Entities.Assurance;
import com.example.pidev.Entities.DetailsLoans;
import com.example.pidev.Entities.User;
import com.example.pidev.Repositories.LoanProjectRepository;
import com.example.pidev.services.DetailsLoansServiceImpl;
import com.example.pidev.services.Iloan;
import com.example.pidev.services.LoanProjectServiceImpl;
import com.example.pidev.Entities.LoanProject;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/Project")
public class LoanProjectController {


    @Autowired
    LoanProjectServiceImpl loanProject;


    @Autowired
    LoanProjectRepository projectRepository;
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
    @PutMapping("/{Idprojet}")
    public LoanProject AddAssuranceToLoanProject (@PathVariable Long Idprojet, @RequestParam String assurancename)
    {
        LoanProject loanP=loanProject.addAssuranceToProjectByName(Idprojet, assurancename);
        return loanP;
    }
  //  @GetMapping("/projects/all-owners")
   // public List<String> getAllProjectOwners() {
    ///    List<String> projectOwners = loanProject.getAllProjectOwners();
      //  return projectOwners;
    //}
//    @GetMapping("/projects/all-borrowers")
  //  public Map<Long, List<User>> getAllBorrowers() {
    //    Map<Long, List<User>> borrowerMap = loanProject.getAllBorrowers();
      //  return borrowerMap;
    //}




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

 //   @PostMapping("/projects/delete/{id}")
   // public ResponseEntity<?> deleteProject(@PathVariable Long id) {
     //   try {
       //     loanProject.deleteProjectAndDetails(id);
         //   return ResponseEntity.ok().build();
        ///} catch (EntityNotFoundException e) {
           // return ResponseEntity.notFound().build();
        //} catch (Exception e) {
          //  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        //}
    //}

//    @PutMapping("/{projectId}/borrow")
  //  public ResponseEntity<LoanProject> borrow(@PathVariable Long projectId, @RequestParam Float amount) {
    //    LoanProject loanproject = loanProject.updateLoanAmount(projectId,amount);
      //  if (loanProject != null) {
        //    return ResponseEntity.ok(loanproject);
        //} else {
         //   return ResponseEntity.notFound().build();
        //}
    //}

    //    @PutMapping("/updateprojects/{Idprojet}")
//    public ResponseEntity<?> updateLoanAmount(@PathVariable Long Idprojet, @RequestBody DetailsLoans details) {
//        try {
//            LoanProject updatedProject = loanProject.updateLoanAmount(Idprojet,details);
//            return ResponseEntity.ok(updatedProject);
//        } catch (EntityNotFoundException e) {
//            return ResponseEntity.notFound().build();
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
//        }
//    }
  //  @ExceptionHandler(Exception.class)
   // public ResponseEntity<?> handleException(Exception e) {
     //   return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error: " + e.getMessage());
    //}

//    @PutMapping("/updateprojects/{Id}")
//    public ResponseEntity<?> updateLoanAmount(@PathVariable String Id, @RequestBody DetailsLoans details) {
//        try {
//            Long projectId = Long.parseLong(Id);
//            LoanProject updatedProject = loanProject.updateLoanAmount(projectId, details);
//            return ResponseEntity.ok(updatedProject);
//        } catch (EntityNotFoundException e) {
//            return ResponseEntity.notFound().build();
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
//        }
//    }
//@PutMapping("/projects/{id}/loanamount")
//public ResponseEntity<LoanProject> updateLoanAmount(@PathVariable(value = "id") Long id, @RequestBody DetailsLoans details) {
//    LoanProject project = loanProject.updateLoanAmount(id, details);
//    return ResponseEntity.ok(project);
//}




}