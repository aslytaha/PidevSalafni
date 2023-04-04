package com.example.pidev.Entities;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Transaction")
public class Transaction implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer IDtransaction; // clé primaire
    private String clientName;
    private Float Amount;
    private LocalDateTime date;
    private String Transaction_type;
    private Long ribsource;
    private Long ribrecipient ;
    private String type_transaction ;
    private String validationCode;
//
//    @ManyToMany
//  @JsonIgnore
//    List<ClientAccount> clientAccountList;



//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "IDClient", nullable = false)
//    private ClientAccount clientaccount;

    @ManyToOne
    private BankAccount Bank_account;


    @Enumerated(EnumType.STRING)
    @Column(name = "etat")
    private TransactionState etat;
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private ClientAccount compteEmetteur;
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private ClientAccount compteDestinataire;

}




