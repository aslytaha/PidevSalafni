package com.example.pidev.Controller;
import com.example.pidev.Entities.Transaction;
import com.example.pidev.Repository.ClientAccountRepository;
import com.example.pidev.Service.Classes.TransactionService;
import com.example.pidev.Service.Interface.ITransaction;
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

    @PostMapping("/depot")
    public ResponseEntity<?> depot(@RequestParam Integer IDClient, @RequestParam Float Amount) {
        try {
            transactionService.depot(IDClient, Amount);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/retrait")
    public ResponseEntity<?> retrait(@RequestParam Integer IDClient, @RequestParam Float Amount) {
        try {
            transactionService.retrait(IDClient, Amount );
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}




