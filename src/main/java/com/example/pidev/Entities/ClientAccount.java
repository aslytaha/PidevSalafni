package com.example.pidev.Entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Client_Account")
public class ClientAccount {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name="IDClient")
    private Integer IDClient ; // clé primaire
    private float Solde ;
    private Boolean Subscription ;
    private Date ExpirationDate ;
    private  Long rib;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="clientaccount")
    private Set<Transaction> transactions;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="clientaccount")
    private Set<LoanProject> loanproject;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="clientaccount")
    private Set<PartnershipProjects> partnershipproject;



    @OneToOne(mappedBy="clientaccount")
    private User user;

}
