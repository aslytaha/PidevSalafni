package com.example.pidev.Entities;


import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "Transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="IDtransaction")
    private Integer IDtransaction ; // clé primaire
    private Float Amount ;
    private Date date ;
    private String Transaction_type ;

     @ManyToOne
    private ClientAccount clientaccount;
    @ManyToOne
    private BankAccount Bank_account;
}


