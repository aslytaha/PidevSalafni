package com.example.pidev.Services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail (String toEmail,String subject,String body)
    {
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom("medtaha.asly@esprit.tn");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);
        javaMailSender.send(message);

    }
    public void sendEmailLoan(String to,String Subject ,String text) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
        helper.setTo(to);
        helper.setSubject(Subject);
        helper.setText(text);
        javaMailSender.send(message);
    }
}
