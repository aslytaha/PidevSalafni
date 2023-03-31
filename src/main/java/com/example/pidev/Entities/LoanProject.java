package com.example.pidev.Entities;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
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
    private Float loanamount;
    private Float remainingamount;

    private Date startdate;
    private Date finishdate;
    private Integer refundperiod;
    private String owner;
    @Enumerated(EnumType.STRING)
    private actarea activityarea;
    private Boolean validate;

    @ManyToOne
    Assurance Assurances;

    @OneToOne(mappedBy="loanProject", cascade = CascadeType.REMOVE)
    private DetailsLoans detailsLoans;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iduser")
    private User user;
    @ManyToOne
    ClientAccount clientaccount;


}