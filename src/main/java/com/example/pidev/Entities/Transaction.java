package com.example.pidev.Entities;
import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "Transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name="IDtransaction")
    private Integer IDtransaction ; // clé primaire
    @Column(nullable = false,name = "Amount")
    private Float Amount ;
    @Column(nullable = false,name = "date")
    private Date date ;
    @Column(nullable = false,name = "Transaction_type")
    private String Transaction_type ;

     @ManyToOne
    private ClientAccount clientaccount;
    @ManyToOne
    private BankAccount Bank_account;

    public Integer getIDtransaction() {
        return IDtransaction;
    }

    public void setIDtransaction(Integer IDtransaction) {
        this.IDtransaction = IDtransaction;
    }

    public Float getAmount() {
        return Amount;
    }

    public void setAmount(Float amount) {
        Amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTransaction_type() {
        return Transaction_type;
    }

    public void setTransaction_type(String transaction_type) {
        Transaction_type = transaction_type;
    }

}


