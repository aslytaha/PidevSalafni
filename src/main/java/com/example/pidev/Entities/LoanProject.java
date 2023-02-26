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



}
