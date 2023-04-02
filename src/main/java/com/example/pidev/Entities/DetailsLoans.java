package com.example.pidev.Entities;
import com.fasterxml.jackson.annotation.JsonBackReference;
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

    private String BorrowedName;



    @JsonBackReference
    @OneToOne(mappedBy="detailsLoans", cascade = CascadeType.REMOVE)
    private LoanProject loanProject;

}