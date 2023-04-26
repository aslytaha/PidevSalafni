package com.example.pidev.Entities;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "details_loans")
public class DetailsLoans  implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDetails;
    private Float amountborrowed;

    @Temporal(TemporalType.TIMESTAMP)//L'annotation @Temporal est utilisée pour spécifier que l'attribut loandate sera stocké dans la base de données en tant que type de date SQL. L'argument TemporalType.TIMESTAMP précise que la date et l'heure doivent être stockées.
    private Date loanDate;


    private String BorrowedName;


    @JsonBackReference
    @ManyToOne
    private LoanProject loanProject;

    @PrePersist
    public void setLoanDate() {
        this.loanDate = new Date();  //permet de modifier la date de création si nécessaire.

    }
}