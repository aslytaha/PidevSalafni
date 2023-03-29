package com.example.pidev.Entities;


import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="Client_Account")
public class ClientAccount {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name="IDClient")
    private Integer IDClient ; // clé primaire
    private Integer Solde ;
    private Boolean Subscription ;
    private Date ExpirationDate ;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="clientaccount")
    private Set<Transaction> transactions;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="clientaccount")
    private Set<LoanProject> loanproject;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="clientaccount")
    private Set<RequestPartnership> requestPartnerships;



    @OneToOne(mappedBy="clientaccount")
    private User user;

}
