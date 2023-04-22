package com.example.pidev.Entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
<<<<<<< Updated upstream
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
=======
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
>>>>>>> Stashed changes
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Client_Account")
<<<<<<< Updated upstream
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class ClientAccount implements Serializable {
=======
public class ClientAccount implements Serializable {

>>>>>>> Stashed changes
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "idclient")
    private Integer IDClient ; // clé primaire
    private Float Solde ;
    private Boolean Subscription ;
<<<<<<< Updated upstream
    private Date ExpirationDate ;

    public Integer getIDClient() {
        return this.IDClient;
    }

    public ClientAccount(int IDClient) {
        this.IDClient = IDClient;
    }






    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="clientaccount",fetch = FetchType.EAGER)
    private Set<RequestPartnership> requestPartnerships;

    @JsonBackReference
    @OneToOne(mappedBy="clientaccount")
    private User user;
=======
    private String ExpirationDate ;
    private  Long rib;
    @JsonBackReference
    @OneToMany(mappedBy = "compteEmetteur")
    Set<Transaction> transactionSet;
    @JsonBackReference
    @OneToMany(mappedBy = "compteDestinataire")
    Set<Transaction> transactionS;



//    @OneToMany(cascade = CascadeType.ALL, mappedBy="clientaccount")
//    private Set<LoanProject> loanproject;
//
//    @OneToMany(cascade = CascadeType.ALL, mappedBy="clientaccount")
//    private Set<PartnershipProjects> partnershipproject;



>>>>>>> Stashed changes

    @JsonIgnore
    @OneToMany(mappedBy = "compteEmetteur")
    Set<Transaction> transactionSet;
    @JsonIgnore
    @OneToMany(mappedBy = "compteDestinataire")
    Set<Transaction> transactionS;




}


