package com.example.pidev.Controllers;

import com.example.pidev.Entities.Assurance;

import com.example.pidev.Services.AssuranceService;
import com.example.pidev.Services.LoanProjectServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/assurance")
public class AssuranceController {
    AssuranceService assuranceService;
    LoanProjectServiceImpl loanProjectService;
    @PostMapping("/add")
    public Assurance addassurance(@RequestBody Assurance as){

        return assuranceService.addAssurance(as);
    }
    @PutMapping("/update")
    public Assurance updateassurance(@RequestBody Assurance as){
        return assuranceService.updateAssurance(as);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteAssurance(@PathVariable("id") Integer asID){
        assuranceService.deleteAssurance(asID);
    }
    @GetMapping("/getAll")
    public List<Assurance> getAllAssurance(){
        return assuranceService.getAllAssurance();
    }
    @GetMapping("/getByID/{id}")
    public Assurance getByAssurance(@PathVariable("id") Integer asID){
        return assuranceService.getAssuranceById(asID);
    }
    //@PostMapping("/add-AssuranceLoanProject{Idprojet}")
 //   @PutMapping("/{Idprojet}/assurance")
   // public Assurance AddAssuranceToLoanProject (@PathVariable Long Idprojet, @RequestParam String assurancename)
    //{
     //   loanProjectService.addAssuranceToProjectByName(Idprojet,assurancename);
      //  return (Assurance) ResponseEntity.ok();
    //}
}
