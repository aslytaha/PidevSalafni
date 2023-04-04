package com.example.pidev.Entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Email {

    private String to;
    private String subject ;
    private String text;
}
