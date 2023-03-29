package com.example.pidev.services;
import com.example.pidev.Entities.ClientAccount;
import com.example.pidev.Repositories.ClientAccountRepository;
import com.example.pidev.Repositories.TransactionRepository;
import com.example.pidev.services.IClientAccount;
import com.example.pidev.services.ITransaction;
import com.example.pidev.Entities.Transaction;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service

public class TransactionService implements ITransaction {
    @Autowired
    private ClientAccountRepository clientAccountRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    IClientAccount  iClientAccount;

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
    @Override
    public int addTransaction(Transaction s) {
        Long source = s.getRibsource();
        Long des = s.getRibrecipient();
        List<ClientAccount> clientAccountList = iClientAccount.selectAll();
        for (ClientAccount clientAccount : clientAccountList)
        {
            if (clientAccount.getRib()==source) {
                float sold = clientAccount.getSolde();
                clientAccount.setSolde(sold +s.getAmount());
                if (clientAccount.getRib()==des) {
                    float a = clientAccount.getSolde();

                    clientAccount.setSolde(a +s.getAmount());}}
        }
        transactionRepository.save(s);
        return 0;
    }
    @Override
    public String approveTransaction(Transaction s) throws MessagingException {
        int code =0;

        if((addTransaction(s)==code))
        {
            transactionRepository.save(s);
            return "transaction approuvée " ;
        }
        else
        {
            return "Transaction non approuvée" ;
        }

    }





}