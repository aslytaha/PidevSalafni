package com.example.pidev.Service.Classes;
import com.example.pidev.Repository.TransactionRepository;
import com.example.pidev.Service.Interface.ITransaction;
import com.example.pidev.Entities.Transaction;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@AllArgsConstructor
@Service

public class TransactionService implements ITransaction {
    private TransactionRepository transactionRepository;

    @Override
    public Transaction addTransaction(Transaction t) {
        return transactionRepository.save(t);
    }
    @Override
    public List<Transaction> getAllTransaction() {
        return transactionRepository.findAll();
    }
    @Override
    public Transaction getTransactionById(Integer IDtransaction) {
        return  transactionRepository.findById(IDtransaction).get();
    }
    @Override
    public Transaction updateTransaction(Transaction t) {
        return transactionRepository.save(t);
    }
    @Override
    public void delete(Integer IdTransaction) {
         transactionRepository.deleteById(IdTransaction);
    }










    }


