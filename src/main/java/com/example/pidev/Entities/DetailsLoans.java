package com.example.pidev.Entities;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "details_loans")
public class DetailsLoans  implements Serializable  {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="IdDetails")
    private Integer idDetails;
 //   private Long Idprojet;

    private float amountborrowed;
    private Date LoanDate;
    private Date RefundDate;
    private float RefundAmount;
    private Integer nbborrowers;
    private String BorrowedName;
    @Enumerated(EnumType.STRING)
    private com.example.pidev.Entities.Status Status;



    @OneToOne(mappedBy="detailsLoans", cascade = CascadeType.REMOVE)
    private LoanProject loanProject;
}