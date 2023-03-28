package com.example.pidev.Entities;


import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Client_Account")
public class ClientAccount {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)

    private Integer IDClient ; // clé primaire
    private float Solde ;
    private Boolean Subscription ;
    private String ExpirationDate ;

    private  Long rib;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="clientaccount")
    private Set<Transaction> transactions;
    @OneToMany(cascade = CascadeType.ALL, mappedBy="clientaccount")
    private Set<LoanProject> loanproject;
    @OneToMany(cascade = CascadeType.ALL, mappedBy="clientaccount")
    private Set<PartnershipProjects> partnershipproject;
    @OneToOne(mappedBy="clientaccount")
    private User user;

    @Override
    public String toString() {
        return "ClientAccount{" +
                "IDClient=" + IDClient +
                ", Solde=" + Solde +
                ", Subscription=" + Subscription +
                ", ExpirationDate='" + ExpirationDate + '\'' +
                ", transactions=" + transactions +
                ", loanproject=" + loanproject +
                ", partnershipproject=" + partnershipproject +
                ", user=" + user +
                '}';
    }
}


