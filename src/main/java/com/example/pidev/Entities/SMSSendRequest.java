package com.example.pidev.Entities;

import lombok.*;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SMSSendRequest {
    private String  destinationSMSNumber;
    private String smsMessage;
}
