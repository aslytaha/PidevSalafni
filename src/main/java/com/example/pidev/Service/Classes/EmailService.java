package com.example.pidev.Service.Classes;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service


public class EmailService  {

     JavaMailSender mailSender;

    public void sendEmail (String to,String subject,String text) throws MessagingException {
        MimeMessage message=mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text);
        mailSender.send(message);

    }
}
