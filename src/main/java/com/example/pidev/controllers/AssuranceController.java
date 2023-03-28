package com.example.pidev.controllers;

import com.example.pidev.Entities.Assurance;
import com.example.pidev.services.AssuranceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/assurance")
public class AssuranceController {
    AssuranceService assuranceService;
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
    @PostMapping("/add-AssuranceLoanProject{Idprojet}")
    public Assurance AddAssuranceToLoanProject (@RequestBody Assurance a , @PathVariable("Idprojet") Long Idprojet)
    {
        Assurance assurance=assuranceService.addAssuranceAndAssignToLoanProject(a,Idprojet);
        return assurance;
    }
}
