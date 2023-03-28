package com.example.pidev.controllers;

import com.example.pidev.Entities.BankAccount;
import com.example.pidev.services.BankAccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public class BankAccountController {
    BankAccountService bankAccountService;
    @PostMapping("/add")
    public BankAccount addbankaccount(@RequestBody BankAccount ba){
        return bankAccountService.addBankAccount(ba);
    }
    @PutMapping("/update")
    public BankAccount updatebankaccount(@RequestBody BankAccount ba){
        return bankAccountService.updateBankAccount(ba);
    }
  //  @DeleteMapping("/delete/{id}")
   // public void deleteBankAccount(@PathVariable("id") Integer baId){
    //    bankAccountService.deleteBankAccount(baId);
   // }

}
