package com.example.pidev.Entities;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table( name = "Users")
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

    @OneToOne
    private ClientAccount clientaccount;

    // constructeurs, getters et setters
}


