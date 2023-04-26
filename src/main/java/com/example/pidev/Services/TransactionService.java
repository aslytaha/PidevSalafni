package com.example.pidev.Services;

import com.example.pidev.Entities.ClientAccount;
import com.example.pidev.Entities.Transaction;
import com.example.pidev.Enumerations.TransactionState;
import com.example.pidev.Entities.User;
import com.example.pidev.Interfaces.ITransaction;
import com.example.pidev.Repositories.ClientAccountRepository;
import com.example.pidev.Repositories.TransactionRepository;
import com.example.pidev.Repositories.UserRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@Service
@AllArgsConstructor
@NoArgsConstructor
public class TransactionService implements ITransaction {

    @Autowired
    ClientAccountService clientAccountService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private ClientAccountRepository clientAccountRepository;
    @Autowired
    private TransactionRepository transactionRepository;




@Autowired
SMSServiceTransaction smsService;


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void transfert(Integer IdEmmeteur, Integer IdDestinataire,  Float montant , String type_transaction) throws Exception {
        ClientAccount compteEmetteur = clientAccountRepository.findById(IdEmmeteur).orElseThrow(() -> new Exception("Compte source non trouvé"));
        ClientAccount compteDestinataire = clientAccountRepository.findById(IdDestinataire).orElseThrow(() -> new Exception("Compte destination non trouvé"));

        float soldeSrc = compteEmetteur.getSolde() - montant;
        if (compteEmetteur.getSolde() < montant) {
            throw new Exception("Solde insuffisant");
        }
        compteEmetteur.setSolde(soldeSrc);
        clientAccountRepository.save(compteEmetteur);



        float soldeDest = compteDestinataire.getSolde() + montant;
        compteDestinataire.setSolde(soldeDest);
        clientAccountRepository.save(compteDestinataire);


        Transaction transactionSrc = new Transaction();
        transactionSrc.setCompteEmetteur(compteEmetteur);
        transactionSrc.setCompteDestinataire(compteDestinataire);
        transactionSrc.setAmount(montant);
        LocalDate now=LocalDate.now();
        Date daten= java.sql.Date.valueOf(now);
        transactionSrc.setDate(daten);
        transactionSrc.setType_transaction(type_transaction);
        transactionSrc.setEtat(TransactionState.PENDING);
        transactionRepository.save(transactionSrc);

    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void depot(Integer compteDestinataire, Float montant, String type_transaction) throws Exception {
        ClientAccount clientAccount = clientAccountRepository.findById(compteDestinataire).orElseThrow(() -> new Exception("Compte non trouvé"));
        float solde = clientAccount.getSolde() + montant;
        clientAccount.setSolde(solde);

        clientAccountRepository.save(clientAccount);
        Transaction transaction = new Transaction();
        transaction.setCompteDestinataire(clientAccount);
        transaction.setAmount(montant);
        LocalDate now=LocalDate.now();
        Date daten= java.sql.Date.valueOf(now);
        transaction.setDate(daten);
        transaction.setType_transaction(type_transaction);
        transaction.setEtat(TransactionState.PENDING);
        transactionRepository.save(transaction);

        User user =userRepository.findUserByClientaccount(compteDestinataire);
        smsService.sendSMS(String.valueOf(user.getPhone()));

    }





    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void retrait(Integer compteDestinataire, float montant, String type_transaction) throws Exception {
        ClientAccount clientAccount = clientAccountRepository.findById(compteDestinataire).orElseThrow(() -> new Exception("Compte non trouvé"));
        float solde = clientAccount.getSolde() - montant;
        if (montant > clientAccount.getSolde()) {
            throw new Exception("Solde insuffisant");
        }
        clientAccount.setSolde(solde);
        clientAccountRepository.save(clientAccount);
        Transaction transaction = new Transaction();
        transaction.setCompteEmetteur(clientAccount);
        transaction.setAmount(-montant);
        LocalDate now=LocalDate.now();
        Date daten= java.sql.Date.valueOf(now);
        transaction.setDate(daten);
        transaction.setType_transaction(type_transaction);
        transaction.setEtat(TransactionState.PENDING);
        transactionRepository.save(transaction);
        User user =userRepository.findUserByClientaccount(compteDestinataire);
        smsService.sendSMS(String.valueOf(user.getPhone()));
    }


@Override
    public List<Transaction> findAllTransaction() {
        return transactionRepository.findAll();
    }
@Override
    public Transaction findTransactionById(Integer IDtransaction) {
        return transactionRepository.findById(IDtransaction).get();
    }
@Override
    public void deleteTransactionById(Integer IDtransaction) {
        transactionRepository.deleteById(IDtransaction);
    }
}





/*
    @Transactional
//      (propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void depot(Integer IDClient, float Amount) throws Exception {
        ClientAccount clientAccount = clientAccountRepository.findById(IDClient).orElseThrow(() -> new Exception("Compte non trouvé"));
        float solde = clientAccount.getSolde() + Amount;
        clientAccount.setSolde(solde);
        clientAccountRepository.save(clientAccount);
        Transaction transaction = new Transaction();
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
        Transaction transaction = new Transaction();
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
        for (ClientAccount clientAccount : clientAccountList) {
            if (clientAccount.getRib() == source) {
                float sold = clientAccount.getSolde();
                clientAccount.setSolde(sold + s.getAmount());
                if (clientAccount.getRib() == des) {
                    float a = clientAccount.getSolde();

                    clientAccount.setSolde(a + s.getAmount());
                }
            }
        }
        transactionRepository.save(s);
        return 0;
    }

    @Override
    public String approveTransaction(Transaction s) throws MessagingException {
        int code = 0;

        if ((addTransaction(s) == code)) {
            transactionRepository.save(s);
            return "transaction approuvée ";
        } else {
            return "Transaction non approuvée";
        }

    }
}

*/



