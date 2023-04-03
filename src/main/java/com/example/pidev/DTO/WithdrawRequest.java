package com.example.pidev.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WithdrawRequest {
    private Integer compteDestinataire;
    private float montant;
    private String typeTransaction;
}
