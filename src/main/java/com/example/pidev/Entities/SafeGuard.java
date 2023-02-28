package com.example.pidev.Entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
    @Table( name = "safeguard")
    public class SafeGuard implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="id")
        private Long id;
        private String doc;
        private Date date;


    @ManyToOne
    LoanProject Loanproject;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public LoanProject getLoanproject() {
        return Loanproject;
    }

    public void setLoanproject(LoanProject loanproject) {
        Loanproject = loanproject;
    }
}
