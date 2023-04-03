//package com.example.pidev.Service.Classes;
//
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;
//
//@Service
//
//public class EmailService  {
//     JavaMailSender javaMailSender;
//
//    public void sendEmail (String toEmail,String subject,String body)
//    {
//        SimpleMailMessage message=new SimpleMailMessage();
//        message.setFrom("belhassen.knani@esprit.tn");
//        message.setTo(toEmail);
//        message.setText(body);
//        message.setSubject(subject);
//        javaMailSender.send(message);
//
//    }
//}
