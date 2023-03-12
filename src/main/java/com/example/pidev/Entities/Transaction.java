package com.example.pidev.Entities;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;


@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer IDtransaction; // clé primaire
    private Float Amount;
    private LocalDateTime date;
    private String Transaction_type;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDClient", nullable = false)
    private ClientAccount clientaccount;

    @ManyToOne
    private BankAccount Bank_account;

}




