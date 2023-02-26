package com.example.pidev.Entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
    @Table( name = "safeguard")
    public class SafeGuard implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="id")
        private Long id;
        private String doc;
        private Date date;


    @ManyToOne
    LoanProject Loanproject;
}
