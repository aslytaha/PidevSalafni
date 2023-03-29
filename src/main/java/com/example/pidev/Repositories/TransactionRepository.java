package com.example.pidev.Repositories;
import com.example.pidev.Entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository  extends JpaRepository<Transaction, Integer> {
}
