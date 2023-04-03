package com.example.pidev.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@AllArgsConstructor
//@NoArgsConstructor
@Getter
@Setter
@Table(name = "amortization")
public class Amortization implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Idamor")
    private Long Idamor;

    @Column(name = "NumDetails")
    private Integer NumDetails;

    @Column(name = "principal")
    private float principal;

    @Column(name = "interest")
    private float interest;

    @Column(name = "total")
    private float total;

    @Column(name = "payment_date")
    private Date paymentDate;

    @Column(name = "payment_number")
    private int paymentNumber;

    @Column(name = "payment_amount")
    private float paymentAmount;

    @Column(name = "remaining_amount")
    private float remainingAmount;

    @Enumerated(EnumType.STRING)
    private Status status;

    public Amortization() {
        this.NumDetails = (int) (Math.random() * 1000000);
    }

@JsonBackReference
    @ManyToOne(fetch=FetchType.LAZY)
    private LoanProject loanproject;
}
