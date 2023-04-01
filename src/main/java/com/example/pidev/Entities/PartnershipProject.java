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
import java.util.Date;
import java.util.Set;


@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table( name = "PartnershipProject")
public class PartnershipProject implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name="idPartnership")
    private Long idPartnership; // Clé primaire
    private float shareofProject;
    private Long amountRequested;
    private Long amountTotal;
    private String projectStage;
    private Date startDate;
    private Date finishDate;
    private String projectName;
    private String descriptionProject;
    private String activityArea;
    @Enumerated(EnumType.STRING)
    private Act act=Act.owner;
    @Enumerated(EnumType.STRING)
    private Statu statu=Statu.en_cours;




    @OneToMany( mappedBy="partnershipProjects",cascade = CascadeType.ALL)
    @JsonManagedReference("request-project")
    private Set<RequestPartnership> requestPartnerships ;

    @JsonIgnoreProperties("user")
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    User user;




}

