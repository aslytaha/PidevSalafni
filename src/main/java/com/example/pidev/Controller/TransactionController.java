package com.example.pidev.Controller;
import com.example.pidev.Entities.Transaction;
import com.example.pidev.Service.Classes.TransactionService;
import com.example.pidev.Service.Interface.ITransaction;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/add")
    public Transaction addTransaction(@RequestBody Transaction t) {
        return transactionService.addTransaction(t);
    }


    @PutMapping("/update")
    public Transaction updateTransaction(@RequestBody Transaction t) {
        return transactionService.updateTransaction(t);
    }


    @DeleteMapping("/delete/{IDtransaction}")

        public void deleteTransaction(@PathVariable("IDtransaction") Integer IDtransaction)
        {
            transactionService.delete(IDtransaction);
        }


        @GetMapping("/getAll")
    public List<Transaction> getAllTransaction(){
        return transactionService.getAllTransaction();
        }


        @GetMapping("/getByID/{IDtransaction}")
public Transaction getByTransaction(@PathVariable("IDtransaction") Integer IDtransaction)
        {
            return transactionService.getTransactionById(IDtransaction);
        }

    }

