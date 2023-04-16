package com.example.pidev.services;

import com.example.pidev.Entities.Assurance;
import com.example.pidev.Entities.Transaction;

import javax.mail.MessagingException;
import java.util.List;


public interface ITransaction {
    void depot(Integer IDClient, float Amount)throws Exception;
    void retrait(Integer IDClient, float Amount)throws Exception;

    int addTransaction(Transaction s);
    public List<Transaction> getAllTransactios();

    String approveTransaction(Transaction s) throws MessagingException;
}
