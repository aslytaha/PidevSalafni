package com.example.pidev.controllers;

import com.example.pidev.Entities.Assurance;
import com.example.pidev.Entities.Transaction;
import com.example.pidev.services.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/transactions")
public class TransactionController {

    TransactionService transactionService;
    @GetMapping("/getAll")
    public List<Transaction> getAllTransactions(){
        return transactionService.getAllTransactios();
    }
}
