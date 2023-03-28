package com.example.pidev.Entities;


import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table( name = "Users")
public class User implements Serializable  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iduser", length = 255)
    private Long idUser;

    private String  Username;

    private String Password;

    private String Email;

    private String FirstName;
    private String LastName;

    private String Address;
    private Date BirthDate;
    private Long Phone;
    private Long CIN;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "Users_Role",
            joinColumns = @JoinColumn(name = "iduser"),
            inverseJoinColumns = @JoinColumn(name = "idRole"))

    private Set<Role> Roles;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Profile> Profiles;

    @OneToOne
    private ClientAccount clientaccount;




}


