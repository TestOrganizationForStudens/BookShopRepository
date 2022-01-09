package com.example.demoBookShop.smsEmailSender;

import com.example.demoBookShop.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service

public class EmailSender implements MessageSender{
    @Autowired
    private JavaMailSenderImpl emailSenderService;

    @Override
    public void sendMessage(User user) {
        String toEmail= user.getEmail();
        String fromEmail= user.getEmail();
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setFrom(fromEmail);
        mailMessage.setTo(toEmail);
        mailMessage.setSubject("Confirmation Email for BookShop");
        mailMessage.setText("Hello from BookSpot, Thank YOU for SignIn");
       // emailSenderService.send(mailMessage);
    }


}
