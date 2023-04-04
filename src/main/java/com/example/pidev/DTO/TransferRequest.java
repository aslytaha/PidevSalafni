package com.example.pidev.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransferRequest {
    private Integer compteEmetteur;
    private Integer compteDestinataire;
    private Float montant;
    private String type_transaction;
}
