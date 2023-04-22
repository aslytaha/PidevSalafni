package com.example.pidev.Entities;

import com.example.pidev.DTO.Response;
import com.example.pidev.Enumerations.Etat;
import com.example.pidev.Enumerations.StatusTransaction;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity

public class Reclamation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     int reclamationId;
     LocalDate creationDate;
     String title;
     String category;
     String details;

   @Enumerated(EnumType.STRING)
   StatusTransaction status = StatusTransaction.EN_ATTENTE;
    @Enumerated(EnumType.STRING)
    Etat etat = null;
    //Etat etat = null;
    @ManyToOne
    @JsonIgnore
    User client;

     @OneToOne(mappedBy = "reclamation")
     @JsonIgnore
     Response response;





}
