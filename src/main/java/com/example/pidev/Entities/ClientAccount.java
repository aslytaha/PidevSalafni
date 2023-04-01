package com.example.pidev.Entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="Client_Account")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class ClientAccount implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name="IDClient")
    private Integer IDClient ; // clé primaire
    private Integer Solde ;
    private Boolean Subscription ;
    private Date ExpirationDate ;

    public Integer getIDClient() {
        return this.IDClient;
    }

    public ClientAccount(int IDClient) {
        this.IDClient = IDClient;
    }


    @OneToMany(cascade = CascadeType.ALL, mappedBy="clientaccount")
    private Set<Transaction> transactions;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="clientaccount")
    private Set<LoanProject> loanproject;

    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy="clientaccount",fetch = FetchType.EAGER)
    private Set<RequestPartnership> requestPartnerships;

    @OneToOne(mappedBy="clientaccount")
    private User user;



}
