package com.example.pidev.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    private float winPercentage;
    private String description;
    private String request;
    @Enumerated(EnumType.STRING)
    private Act act=Act.partner;



    @ManyToOne
    @JsonBackReference("request-project")
    PartnershipProject partnershipProjects;
    @JsonBackReference
    @JsonIgnoreProperties("clientaccount") // ignore clientaccount property during serialization
    @ManyToOne(fetch = FetchType.LAZY)
   // @JoinColumn(name = "IDClient")
    ClientAccount clientaccount;

}

