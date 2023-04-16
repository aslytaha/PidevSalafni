package com.example.pidev.services;

import com.example.pidev.Entities.BankAccount;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface IBankAccountService {
    public BankAccount addBankAccount(BankAccount ba);
    public BankAccount updateBankAccount(BankAccount ba);

    //  @Override
     // public void deleteBankAccount(Integer baId) {
       //   bankAccount.deleteById(baId);
     // }
   // @Scheduled(cron = "0 0 18 * * *")
    //  @Transactional
     // void addRemainingAmountToLoanProject(String bankAccountId, String loanProjectId);

    //  @Override
     // public void deleteBankAccount(Integer baId) {
       //   bankAccount.deleteById(baId);
     // }
     // void addRemainingAmountToLoanProject();
    // public void deleteBankAccount(Integer baId);
}
