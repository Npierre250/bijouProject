package com.memory.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailSenderServiceConfig {
    @Autowired
    private JavaMailSender mailsender;
    public void sendEmail(String toemail,String subject,String body) throws MessagingException
    {

        MimeMessage message = mailsender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setSubject(subject);
        helper.setTo(toemail);
        helper.setText(body, true);
        mailsender.send(message);       
    }
    public void sendSignUpEmail(String toemail,String subject,String username) throws MessagingException
    {
        String message="<!doctype html><html><head><title>Sign up</title><body><h1 style='background-color:grey'>Decease</h1><div style='background-color:white,border:1px solid black,border-radius:15'><h4>Welcome Page</h4><p>Dear "+username+", your account has been created successfully</p>"+
        "<a href='http://localhost:8000'>Visit website</a></div></div></body></html>";
                subject="Create account";
            this.sendEmail(toemail, subject, message);
    }
// @EventListener(ApplicationReadyEvent.class)
//     public void sendEmail() throws MessagingException
//     { 
//         this.sendEmail("rukundojustin372@gmail.com","Login Successfully","<!doctype html><html lang=\"en\"><head><title>Sign up</title><body><h1 style='background-color:grey'>Decease</h1><div style='background-color:white,border:1px solid black,border-radius:15'><h4>Welcome Page</h4><p>Dear , your account has been created successfully</p>"+
//         "<a href='http://localhost:8000'>Visit website</a></div></div></body></html>");
// System.out.println("Executed");   
    // }

}
