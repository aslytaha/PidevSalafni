package com.example.pidev.services;

import com.example.pidev.Entities.*;
import com.example.pidev.Repositories.BankAccountRepository;
import com.example.pidev.Repositories.LoanProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class BankAccountService implements IBankAccountService{
    BankAccountRepository bankAccount;
    LoanProjectRepository loanProjectRepository;
    @Override
    public BankAccount addBankAccount(BankAccount ba) {
        return bankAccount.save(ba);
    }

    @Override
    public BankAccount updateBankAccount(BankAccount ba) {
        return bankAccount.save(ba);
    }

  //  @Override
   // public void deleteBankAccount(Integer baId) {
     //   bankAccount.deleteById(baId);
   // }
 // @Scheduled(cron = "0 0 18 * * *")
}
