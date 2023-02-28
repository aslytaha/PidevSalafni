package com.example.pidev.services;

import com.example.pidev.Entities.BankAccount;
import com.example.pidev.Repositories.BankAccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@AllArgsConstructor
@Service
public class BankAccountService implements IBankAccountService{
BankAccountRepository bankAccount;
    @Override
    public BankAccount addBankAccount(BankAccount ba) {
        return bankAccount.save(ba);
    }

    @Override
    public BankAccount updateBankAccount(BankAccount ba) {
        return bankAccount.save(ba);
    }

    @Override
    public List<BankAccount> getAllBankAccount() {
        return bankAccount.findAll();
    }

    @Override
    public Optional<BankAccount> getBankAccountById(Integer baId) {
        return Optional.ofNullable(bankAccount.findById(baId).orElse(null));
    }

    @Override
    public void deleteBankAccount(Integer baId) {
        bankAccount.deleteById(baId);
    }
}
