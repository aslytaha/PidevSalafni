package com.example.pidev.services;

import com.example.pidev.Entities.BankAccount;

import java.util.List;
import java.util.Optional;

public interface IBankAccountService {
    public BankAccount addBankAccount(BankAccount ba);
    public BankAccount updateBankAccount(BankAccount ba);
   // public void deleteBankAccount(Integer baId);
}
