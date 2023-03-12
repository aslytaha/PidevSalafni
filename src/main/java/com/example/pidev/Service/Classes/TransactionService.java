package com.example.pidev.Service.Classes;
import com.example.pidev.Entities.ClientAccount;
import com.example.pidev.Repository.ClientAccountRepository;
import com.example.pidev.Repository.TransactionRepository;
import com.example.pidev.Service.Interface.ITransaction;
import com.example.pidev.Entities.Transaction;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service

public class TransactionService implements ITransaction {
    @Autowired
    private ClientAccountRepository clientAccountRepository;
    @Autowired
    private TransactionRepository transactionRepository;


    @Transactional
//      (propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void depot(Integer IDClient, float Amount) throws Exception {
        ClientAccount clientAccount = clientAccountRepository.findById(IDClient).orElseThrow(() -> new Exception("Compte non trouvé"));
        float solde = clientAccount.getSolde() + Amount;
        clientAccount.setSolde(solde);
        clientAccountRepository.save(clientAccount);
       Transaction transaction= new Transaction();
        transaction.setClientaccount(clientAccount);
        transaction.setAmount(Amount);
        transaction.setTransaction_type("Depot");
        transaction.setDate(LocalDateTime.now());
        transactionRepository.save(transaction);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void retrait(Integer IDClient, float Amount) throws Exception {
        ClientAccount clientAccount = clientAccountRepository.findById(IDClient).orElseThrow(() -> new Exception("Compte non trouvé"));
        float solde = clientAccount.getSolde() - Amount;
        if (solde < 0) {
            throw new Exception("Solde insuffisant");
        }
        clientAccount.setSolde(solde);
        clientAccountRepository.save(clientAccount);
        Transaction transaction= new Transaction();
        transaction.setClientaccount(clientAccount);
        transaction.setTransaction_type("Retrait");
        transaction.setAmount(Amount);
        transaction.setDate(LocalDateTime.now());
        transactionRepository.save(transaction);
    }





}