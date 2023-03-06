package com.example.pidev.Entities;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="Client_Account")
public class ClientAccount {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name="IDClient")
    private Integer IDClient ; // clé primaire
    @Column(nullable = false,name = "Solde")
    private Integer Solde ;
    @Column(nullable = false,name = "Subscription")
    private Boolean Subscription ;
    @Column(nullable = false,name = "ExpirationDate")
    private String ExpirationDate ;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="clientaccount")
    private Set<Transaction> transactions;
    @OneToMany(cascade = CascadeType.ALL, mappedBy="clientaccount")
    private Set<LoanProject> loanproject;
    @OneToMany(cascade = CascadeType.ALL, mappedBy="clientaccount")
    private Set<PartnershipProjects> partnershipproject;
    @OneToOne(mappedBy="clientaccount")
    private User user;

    public Integer getIDClient() {return IDClient;}

    public void setIDClient(Integer IDClient) {
        this.IDClient = IDClient;
    }

    public Integer getSolde() {
        return Solde;
    }

    public void setSolde(Integer solde) {
        Solde = solde;
    }

    public Boolean getSubscription() {
        return Subscription;
    }

    public void setSubscription(Boolean subscription) {
        Subscription = subscription;
    }

    public String getExpirationDate() {
        return ExpirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        ExpirationDate = expirationDate;
    }

}
