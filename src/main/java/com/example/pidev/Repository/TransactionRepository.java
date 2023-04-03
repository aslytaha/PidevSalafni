package com.example.pidev.Repository;
import com.example.pidev.Entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TransactionRepository  extends JpaRepository<Transaction, Integer> {
    List<Transaction> findByClientName(String clientName);

//    @Query("SELECT t FROM Transaction t WHERE t.etat =?1")
//    List<Transaction> findTransactionsEnAttente(String etat);
}
