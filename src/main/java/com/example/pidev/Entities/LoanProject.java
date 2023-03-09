package com.example.pidev.Entities;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table( name = "loanproject")
public class LoanProject implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Idprojet")
    private Long Idprojet;
    private String projectname;
    private String description;
    private Number loanamount;
    private Date startdate;
    private Date finishdate;
    private Date refundperiod;
    private String owner;
    @Enumerated(EnumType.STRING)
    private actarea activityarea;
    private Boolean validate;


    //    @ManyToOne
//    DetailsLoans detailsloan;
    @OneToOne(mappedBy="loanProject")
    private DetailsLoans detailsLoans;

    @ManyToOne
    ClientAccount clientaccount;


}
