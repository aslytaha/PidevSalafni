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

    public Integer getIdDetails() {
        return idDetails;
    }

    public void setIdDetails(Integer idDetails) {
        this.idDetails = idDetails;
    }

    public Number getAmountborrowed() {
        return amountborrowed;
    }

    public void setAmountborrowed(Number amountborrowed) {
        this.amountborrowed = amountborrowed;
    }

    public Date getLoanDate() {
        return LoanDate;
    }

    public void setLoanDate(Date loanDate) {
        LoanDate = loanDate;
    }

    public Date getRefundDate() {
        return RefundDate;
    }

    public void setRefundDate(Date refundDate) {
        RefundDate = refundDate;
    }

    public Number getRefundAmount() {
        return RefundAmount;
    }

    public void setRefundAmount(Number refundAmount) {
        RefundAmount = refundAmount;
    }

    public String getBorrowedName() {
        return BorrowedName;
    }

    public void setBorrowedName(String borrowedName) {
        BorrowedName = borrowedName;
    }

    public status getStatus() {
        return Status;
    }

    public void setStatus(status status) {
        Status = status;
    }

    public Set<LoanProject> getLoanprojects() {
        return Loanprojects;
    }

    public void setLoanprojects(Set<LoanProject> loanprojects) {
        Loanprojects = loanprojects;
    }
}