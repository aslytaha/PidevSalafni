package com.example.pidev.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail (String toEmail,String subject,String body)
    {
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom("naderbenali1998@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);
        javaMailSender.send(message);

    }
}
