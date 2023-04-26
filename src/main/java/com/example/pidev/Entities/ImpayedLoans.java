package com.example.pidev.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="impayed_loans")
public class ImpayedLoans {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Integer ID ; // clé primaire
    private int paymentNumber;
    private Long iProjet;
    private float AmountNotPayed;
    private float NewAmountToPay;
    private Date NewPaymentDate;
    private Integer InterestRate;
    private String email;
}
