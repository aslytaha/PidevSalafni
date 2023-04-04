package com.example.pidev.Entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    private Float Solde ;
    private Boolean Subscription ;
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

    @JsonIgnore
    @OneToMany(mappedBy = "compteEmetteur")
    Set<Transaction> transactionSet;
    @JsonIgnore
    @OneToMany(mappedBy = "compteDestinataire")
    Set<Transaction> transactionS;




}
