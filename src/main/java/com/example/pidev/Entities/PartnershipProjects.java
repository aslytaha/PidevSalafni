package com.example.pidev.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table( name = "PartnershipProject")
public class PartnershipProjects implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name="idPartnership")
    private Long idPartnership; // Clé primaire
    private Long shareofProject;
    private Long amountRequested;
    private String projectStage;
    private Date startDate;
    private Date finishDate;
    private String projectName;
    private String descriptionProject;
    private String activityArea;
    @Enumerated(EnumType.STRING)
    private Act act;

    @OneToMany( mappedBy="partnershipProjects")
    private Set<RequestPartnership> requestPartnerships ;


    @ManyToOne
    ClientAccount clientaccount;

}

