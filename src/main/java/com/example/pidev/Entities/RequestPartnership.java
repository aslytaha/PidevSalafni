package com.example.pidev.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


import javax.persistence.*;


@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table( name = "RequestPartnership")
public class RequestPartnership implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name="idRequest")
    private Long idRequest; // Clé primaire
    private String partnerName;
    private Long amountPayed;
    private String description;
    private String request;
    @Enumerated(EnumType.STRING)
    private Act act;



    @ManyToOne
    PartnershipProject partnershipProjects;
}

