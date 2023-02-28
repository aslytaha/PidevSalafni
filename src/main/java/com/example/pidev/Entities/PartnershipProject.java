package com.example.pidev.Entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;


@Entity
@Table( name = "PartnershipProject")
public class PartnershipProject implements Serializable {
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


    public Long getIdPartnership() {
        return idPartnership;
    }

    public void setIdPartnership(Long idPartnership) {
        this.idPartnership = idPartnership;
    }

    public Long getShareofProject() {
        return shareofProject;
    }

    public void setShareofProject(Long shareofProject) {
        this.shareofProject = shareofProject;
    }

    public Long getAmountRequested() {
        return amountRequested;
    }

    public void setAmountRequested(Long amountRequested) {
        this.amountRequested = amountRequested;
    }

    public String getProjectStage() {
        return projectStage;
    }

    public void setProjectStage(String projectStage) {
        this.projectStage = projectStage;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDescriptionProject() {
        return descriptionProject;
    }

    public void setDescriptionProject(String descriptionProject) {
        this.descriptionProject = descriptionProject;
    }

    public String getActivityArea() {
        return activityArea;
    }

    public void setActivityArea(String activityArea) {
        this.activityArea = activityArea;
    }

    public Act getAct() {
        return act;
    }

    public void setAct(Act act) {
        this.act = act;
    }

    public Set<RequestPartnership> getRequestPartnerships() {
        return requestPartnerships;
    }

    public void setRequestPartnerships(Set<RequestPartnership> requestPartnerships) {
        this.requestPartnerships = requestPartnerships;
    }

    public ClientAccount getClientaccount() {
        return clientaccount;
    }

    public void setClientaccount(ClientAccount clientaccount) {
        this.clientaccount = clientaccount;
    }

    @OneToMany( mappedBy="partnershipProjects")
    private Set<RequestPartnership> requestPartnerships ;


    @ManyToOne
    ClientAccount clientaccount;

}

