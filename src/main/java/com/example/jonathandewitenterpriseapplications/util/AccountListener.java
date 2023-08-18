package com.example.jonathandewitenterpriseapplications.util;


import com.example.jonathandewitenterpriseapplications.models.UserDetail;
import com.example.jonathandewitenterpriseapplications.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AccountListener implements ApplicationListener<OnCreteAccountEvent> {

    private String serverUrl = "http://localhost:8080/";

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private AccountService accountService;


    @Override
    public void onApplicationEvent(OnCreteAccountEvent event) {
        this.confirmCreateAccount(event);
    }

    private void confirmCreateAccount(OnCreteAccountEvent event) {
        // Create verification token
        UserDetail userDetail = event.getUserDetail();
        String token = UUID.randomUUID().toString();
        // Save verification token
        accountService.createVerificationToken(userDetail, token);
        // make email properties
        String recipientAddress = userDetail.getEmail();
        String subject = "Account Confirmation";
        String confirmationUrl = "account/accountConfirm?token=" + token;
        String message = "Please confirm your account by clicking on the link:";
        // Send email
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + "\r\n" + serverUrl + confirmationUrl);
        mailSender.send(email);

        System.out.println("Send mail complete");
    }
}
