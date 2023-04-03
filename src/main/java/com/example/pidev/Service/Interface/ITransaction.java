package com.example.pidev.Service.Interface;
import com.example.pidev.Entities.Transaction;

import javax.mail.MessagingException;
import java.util.List;


public interface ITransaction {

    List<Transaction> findAllTransaction();

    Transaction findTransactionById(Integer IDtransaction);

    void deleteTransactionById(Integer IDtransaction);
     void depot(Integer compteDestinataire, float montant, String type_transaction) throws Exception;


}
