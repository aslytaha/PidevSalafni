package com.example.pidev.Entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "bank_account")
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="IDbank")
    private Integer IDbank ; // clé primaire
    private Long RIB ;
    private Float solde ;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="Bank_account")
    private Set<Transaction> Transactions;
}
