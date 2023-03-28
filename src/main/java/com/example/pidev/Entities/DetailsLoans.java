package com.example.pidev.Entities;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;



@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "details_loans")
public class DetailsLoans  implements Serializable  {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDetails;
    private Float amountborrowed;
    private Date LoanDate;
    private Date RefundDate;
    private Float RefundAmount;
    private Integer nbborrowers;
    private String BorrowedName;
    @Enumerated(EnumType.STRING)
    private status Status;



    @OneToOne()
    private LoanProject loanProject;
//icimappedby
}