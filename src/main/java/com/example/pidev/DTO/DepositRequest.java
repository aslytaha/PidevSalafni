package com.example.pidev.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class DepositRequest {
    private Integer compteDestinataire;
    private float montant;
    private String typeTransaction;
    private LocalDateTime tempsValidation;
}
