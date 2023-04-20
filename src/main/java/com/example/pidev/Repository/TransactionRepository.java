package com.example.pidev.Repository;
import com.example.pidev.Entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TransactionRepository  extends JpaRepository<Transaction, Integer> {
    List<Transaction> findByClientName(String clientName);



//    @Query("SELECT COUNT(t) FROM Transaction t WHERE t.compteDestinataire.IDClient = ?1 AND t.compteEmetteur.IDClient = ?1")
//    Integer countTransactionsForUser(@Param("IDClient") Integer IDClient);


    @Query("SELECT COUNT(t) FROM Transaction t WHERE t.compteDestinataire.IDClient = ?1 AND t.compteEmetteur.IDClient = ?1")
      Integer countTransactionByCompteDestinataireAndCompteEmetteur(Integer id);

}
