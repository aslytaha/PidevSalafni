package com.example.pidev.Repositories;

import com.example.pidev.Entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository <BankAccount, Integer> {

     BankAccount findBankAccountByRIB(Long RIB);

}
