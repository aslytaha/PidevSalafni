package com.example.pidev.Entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="IDadmin")
    private Integer IDadmin ; // clé primaire
    private String AdminName ;
    private String AdminLastName ;
    private String Mail ;
    private String AdminLogin ;
    private String AdminPassword ;
    private Date BirthDate;

    @OneToOne
    private BankAccount bankaccount;
}
