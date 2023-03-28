package com.example.pidev.Services;


import com.example.pidev.Entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {




        @Autowired
        private JavaMailSender mailSender;

        private String generateVerificationCode() {
            return null;
        }

        public void sendVerificationEmail(User user) {
            String code = generateVerificationCode();
            String subject = "Confirmation de compte";
            String text = "Bonjour " + user.getFirstName() + ",\n\n"
                    + "Veuillez entrer le code de vérification suivant pour confirmer votre compte : " + code;

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(user.getEmail());
            message.setSubject(subject);
            message.setText(text);
            mailSender.send(message);
        }
    }



