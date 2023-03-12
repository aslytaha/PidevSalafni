package com.example.pidev.controllers;

import com.example.pidev.Entities.Assurance;
import com.example.pidev.Entities.ImpayedLoans;
import com.example.pidev.services.AssuranceService;
import com.example.pidev.services.IImpayedLoansService;
import com.example.pidev.services.ImpayedLoansService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class ImpayedLoansController {
    ImpayedLoansService impayedLoansService;
    @PostMapping("/add")
    public ImpayedLoans addImpayedLoans(@RequestBody ImpayedLoans il){
        return impayedLoansService.addImpayedLoans(il);
    }
    @PutMapping("/update")
    public ImpayedLoans updateimpayedloans(@RequestBody ImpayedLoans il){
        return impayedLoansService.updateImpayedLoans(il);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteImpayedLoans(@PathVariable("id") Integer ilID){
        impayedLoansService.deleteImpayedLoans(ilID);
    }
    @GetMapping("/getAll")
    public List<ImpayedLoans> getAllImpayedLoans(){
        return impayedLoansService.getAllImpayedLoans();
    }
    @GetMapping("/getByID/{id}")
    public ImpayedLoans getByImpayedLoans(@PathVariable("id") Integer ilID){
        return impayedLoansService.getImpayedLoansById(ilID);
    }
}
