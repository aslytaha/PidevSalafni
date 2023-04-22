package com.example.pidev.Controllers;

import com.example.pidev.DTO.DepositRequest;
import com.example.pidev.DTO.TransferRequest;
import com.example.pidev.DTO.WithdrawRequest;
import com.example.pidev.Entities.Transaction;

import com.example.pidev.Repositories.ClientAccountRepository;
import com.example.pidev.Repositories.TransactionRepository;
=======
import com.example.pidev.Repositories.ClientAccountRepository;
import com.example.pidev.Repositories.TransactionRepository;

>>>>>>> Stashed changes
import com.example.pidev.Services.TransactionService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    TransactionService transactionService;
    @Autowired
    ClientAccountRepository clientAccountRepository;
    @Autowired
    TransactionRepository transactionRepository;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;

    }

<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
    @PostMapping("/deposit")
    public ResponseEntity<?> depot(@RequestBody DepositRequest depositRequest) {
     Integer  one= depositRequest.getCompteDestinataire();
     float two=depositRequest.getMontant();

        try {
            transactionService.depot(one,two,"DEPOT");
            return ResponseEntity.ok("Transaction created successfully and waiting for validation.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/withdraw")
    public ResponseEntity<?> retrait(@RequestBody WithdrawRequest withdrawRequest ) {
        try {
            transactionService.retrait(withdrawRequest.getCompteDestinataire(), withdrawRequest.getMontant(), "Withdraw");
            return ResponseEntity.ok("Transaction created successfully and waiting for validation.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/GettAll")
    public List<Transaction> showTransactions() {
        List<Transaction> transactions = transactionService.findAllTransaction();
        return transactions;
    }


    @PostMapping("/transfert")
    public ResponseEntity<?> transfert(@RequestBody TransferRequest transferRequest){//@RequestParam Long compteEmetteur, @RequestParam Long compteDestinataire, @RequestParam float montant , @RequestParam String type_transaction) {
        try {
            transactionService.transfert(transferRequest.getCompteEmetteur(), transferRequest.getCompteDestinataire(), transferRequest.getMontant() , "Transfer");
            return ResponseEntity.ok("Transaction created successfully and waiting for validation.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Transaction> findTransactionById(@PathVariable("id") Integer IDtransaction) {
        Transaction transaction = transactionService.findTransactionById(IDtransaction);
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTransactionById(@PathVariable("id") Integer IDtransaction ) {
        transactionService.deleteTransactionById(IDtransaction);
        return new ResponseEntity<>(HttpStatus.OK);

    }




      //// affichage de l'historique de tout les transaction pour chaque client  /////


    public TransactionController(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }
    @GetMapping("/{clientName}")
    public List<Transaction> getTransactionsByClientName(@PathVariable String clientName) {
        return transactionRepository.findByClientName(clientName);
    }


}




