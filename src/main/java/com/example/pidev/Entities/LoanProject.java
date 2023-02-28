package com.example.pidev.Entities;



import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table( name = "loanproject")
public class LoanProject implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Idproj")
    private Long Idprojet;
    private String projectname;
    private String description;
    private Number loanamount;
    private Date startdate;
    private Date finishdate;
    private Integer nbborrowers;
    private Date refundperiod;
    private String owner;
    @Enumerated(EnumType.STRING)
    private actarea activityarea;

    @ManyToOne
    DetailsLoans detailsloan;

    @ManyToOne
    ClientAccount clientaccount;

    public Long getIdprojet() {
        return Idprojet;
    }

    public void setIdprojet(Long idprojet) {
        Idprojet = idprojet;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Number getLoanamount() {
        return loanamount;
    }

    public void setLoanamount(Number loanamount) {
        this.loanamount = loanamount;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getFinishdate() {
        return finishdate;
    }

    public void setFinishdate(Date finishdate) {
        this.finishdate = finishdate;
    }

    public Integer getNbborrowers() {
        return nbborrowers;
    }

    public void setNbborrowers(Integer nbborrowers) {
        this.nbborrowers = nbborrowers;
    }

    public Date getRefundperiod() {
        return refundperiod;
    }

    public void setRefundperiod(Date refundperiod) {
        this.refundperiod = refundperiod;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public actarea getActivityarea() {
        return activityarea;
    }

    public void setActivityarea(actarea activityarea) {
        this.activityarea = activityarea;
    }

    public DetailsLoans getDetailsloan() {
        return detailsloan;
    }

    public void setDetailsloan(DetailsLoans detailsloan) {
        this.detailsloan = detailsloan;
    }

    public ClientAccount getClientaccount() {
        return clientaccount;
    }

    public void setClientaccount(ClientAccount clientaccount) {
        this.clientaccount = clientaccount;
    }
}
