package com.example.pidev.Service.Interface;
import com.example.pidev.Entities.Transaction;
import java.util.List;


public interface ITransaction {

    Transaction addTransaction(Transaction t);
    Transaction updateTransaction(Transaction t);
    void delete(Integer IDtransaction);
    List<Transaction> getAllTransaction();
    Transaction getTransactionById(Integer IDtransaction);
}
