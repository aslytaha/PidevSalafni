package com.example.pidev.Controllers;

import com.example.pidev.Entities.ImpayedLoans;
import com.example.pidev.Services.ImpayedLoansService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@AllArgsConstructor
@RequestMapping("/ImpayedLoans")
public class ImpayedLoansController {
   @Autowired
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
    @PostMapping("/transfer-Impayed")
    public ResponseEntity<String> transferImpayedTransactions() {
        try {
            impayedLoansService.transferImpayedLoans();
            return ResponseEntity.ok("Unpaid transactions transferred to ImpayedLoans successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to transfer unpaid transactions to ImpayedLoans: " + e.getMessage());
        }
    }
    @GetMapping("/SendMailWithNewTransaction")
    public void ImpayedLoans(){
        impayedLoansService.SendMailToLateUser();
    }
}
