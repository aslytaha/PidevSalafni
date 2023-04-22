package com.example.pidev.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
<<<<<<< Updated upstream
=======
import org.hibernate.validator.constraints.UniqueElements;
>>>>>>> Stashed changes

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
<<<<<<< Updated upstream
@Table( name = "Users",uniqueConstraints = { @UniqueConstraint(columnNames = "username"), @UniqueConstraint(columnNames = "email") })
=======
@Table( name = "Users",uniqueConstraints = { @UniqueConstraint(columnNames = "username") })
>>>>>>> Stashed changes
public class User implements Serializable  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iduser", length = 255)
    private Long idUser;



    @NotBlank(message = "Username is required")
    @Size(max = 20)
    @NotNull
    private String  Username;

    @NotNull
    private String Password;

<<<<<<< Updated upstream
    @Email(message = "Email is not valid")
=======
    @Email
>>>>>>> Stashed changes
    private String Email;

    private String FirstName;
    private String LastName;
    private Integer Age;
    private String Address;
    private Date BirthDate;
    private Long Phone;
    private Long CIN;
    private Integer IsVerified;
    private String verificationToken;
    private String verificationCode;
<<<<<<< Updated upstream
=======
    private Integer age;
>>>>>>> Stashed changes


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "Users_Role",
            joinColumns = @JoinColumn(name = "iduser"),
            inverseJoinColumns = @JoinColumn(name = "idRole"))

    private Set<Role> Roles;


    @JsonManagedReference
    @OneToOne
    private ClientAccount clientaccount;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
    private Set<PartnershipProject> partnershipProjects;

<<<<<<< Updated upstream

    @OneToMany(mappedBy = "user")
    private Set<LoanProject> loanProjects;
=======
>>>>>>> Stashed changes




}


