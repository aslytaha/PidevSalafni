package com.example.pidev.Entities;

import javax.persistence.*;
import java.io.Serializable;

    @Entity
    @Table( name = "assurance")
    public class Assurance implements Serializable {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="id")
        private Long id;
        private String name;
        private String adress;
        private String mail;
        private Long phone;

        @ManyToOne
        LoanProject Loanproject;


    }
