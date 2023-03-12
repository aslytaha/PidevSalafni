package com.example.pidev.Entities;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name ="impayed_loans")
public class ImpayedLoans {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Integer ID ; // clé primaire
    private Integer numTrans;
    private Integer AmountNotPayed;
    private Integer NewAmountToPay;
    private Date NewPaymentDate;
    private Number InterestRates;
}
