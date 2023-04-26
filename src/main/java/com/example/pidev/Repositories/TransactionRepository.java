package com.example.pidev.Repositories;
import com.example.pidev.Entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TransactionRepository  extends JpaRepository<Transaction, Integer> {
    List<Transaction> findByClientName(String clientName);



}
