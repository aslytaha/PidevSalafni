package com.example.pidev.Entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table (name = "bank_account")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class BankAccount implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="IDbank")
    private Integer IDbank ; // clé primaire
    private Long RIB ;
    private Float solde ;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="Bank_account")
    private Set<Transaction> Transactions;
}
