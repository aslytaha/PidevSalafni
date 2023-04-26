package com.example.pidev.Interfaces;
import com.example.pidev.Entities.Transaction;

import java.util.List;


public interface ITransaction {

    List<Transaction> findAllTransaction();

    Transaction findTransactionById(Integer IDtransaction);

    void deleteTransactionById(Integer IDtransaction);
     void depot(Integer compteDestinataire, Float montant, String type_transaction) throws Exception;

    void transfert(Integer compteEmetteur, Integer compteDestinataire, Float montant, String type_transaction) throws Exception;
}
