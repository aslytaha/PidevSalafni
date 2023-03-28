package com.example.pidev.Entities;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;



@Entity
//@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "details_loans")
public class DetailsLoans  implements Serializable  {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="IdDetails")
    private Integer idDetails;
    private Number amountborrowed;
    private Date LoanDate;
    private Date RefundDate;
    private Number RefundAmount;
    private String BorrowedName;
    @Enumerated(EnumType.STRING)
    private status Status;

    @OneToMany(mappedBy = "detailsloan")
    private Set<LoanProject> Loanprojects;
    @OneToOne()
    private LoanProject loanProject;


}