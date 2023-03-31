package com.example.pidev.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Table( name = "assurance")
    public class Assurance implements Serializable {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="id")
        private Integer id;
        private String name;
        private String adress;
        private String mail;
        private Long phone;

        @OneToMany(mappedBy = "Assurances", cascade = CascadeType.ALL)
        private Set<LoanProject> loanProjects;



    }
