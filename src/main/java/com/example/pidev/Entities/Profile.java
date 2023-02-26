package com.example.pidev.Entities;



import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table( name = "Profile")
public class Profile implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idprofile" )
    private Long id;

    private String type;
    @ManyToOne
    User user;
}
