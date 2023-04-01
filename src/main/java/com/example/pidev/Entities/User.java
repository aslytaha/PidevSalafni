package com.example.pidev.Entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table( name = "Users")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="iduser" , length = 255)
    private Long id;

    private String Login;

    private String password;

    private String email;

    private String name;
    private String LastName;
    private String Adress;
    private Date BirthDate;
    private Long Phone;
    private Long CIN;
    private String Role;


    @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
    private Set<Profile> Profiles;

    @JsonBackReference
    @OneToOne
    private ClientAccount clientaccount;


    // constructeurs, getters et setters

    @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
    private Set<PartnershipProject> partnershipProjects;

}


