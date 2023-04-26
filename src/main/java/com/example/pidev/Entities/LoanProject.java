package com.example.pidev.Entities;



import com.example.pidev.Enumerations.type;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    private Float loanamount;
    private Float remainingamount;

    private Date startdate;
    private Date finishdate;
    private Date refundperiod;
    private String owner;
    @Enumerated(EnumType.STRING)
    private type paymenttype;
    private Boolean validate;


    @JsonManagedReference
    @OneToMany(mappedBy = "loanProject")
    private Set<DetailsLoans> detailsLoansSet;


@JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iduser")
    private User user;

    public void setAmortizationTable(List<Amortization> amortizationTable) {
        this.amortizationTable = amortizationTable;
    }
    @OneToMany(mappedBy = "loanproject", cascade = CascadeType.ALL)
    private List<Amortization> amortizationTable;


    @ManyToOne
    Assurance Assurance;


}
