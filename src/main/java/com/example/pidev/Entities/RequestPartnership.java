package com.example.pidev.Entities;

import java.io.Serializable;


import javax.persistence.*;


@Entity
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

    public Long getIdRequest() {
        return idRequest;
    }

    public void setIdRequest(Long idRequest) {
        this.idRequest = idRequest;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public Long getAmountPayed() {
        return amountPayed;
    }

    public void setAmountPayed(Long amountPayed) {
        this.amountPayed = amountPayed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public Act getAct() {
        return act;
    }

    public void setAct(Act act) {
        this.act = act;
    }

    public PartnershipProject getPartnershipProjects() {
        return partnershipProjects;
    }

    public void setPartnershipProjects(PartnershipProject partnershipProjects) {
        this.partnershipProjects = partnershipProjects;
    }

    @ManyToOne
    PartnershipProject partnershipProjects;
}

