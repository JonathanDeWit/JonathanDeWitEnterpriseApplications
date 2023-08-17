package com.example.jonathandewitenterpriseapplications.util;

import com.example.jonathandewitenterpriseapplications.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class OrderListener implements ApplicationListener<OnCreateOrderEvent> {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void onApplicationEvent(OnCreateOrderEvent event) {
        //get email properties

        User user = event.getUser();
        var orderItems = event.getOrderItems();

        double totalPrice = orderItems.stream()
                .mapToDouble(item -> item.getProduct().getPrice().doubleValue() * item.getQuantity())
                .sum();


        String recipientAddress = user.getUsername();
        String subject = "Ventistore Order";
        String message = "Than you for your order\nYour order includes:\n";

        for (var item:orderItems) {
            message += "\n- "+ item.getQuantity() +" . " + item.getProduct().getName() + " " + item.getProduct().getPrice();
        }

        message += "\n\nThe total price of the order is: "+totalPrice+"€";
        //send email
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message);
        mailSender.send(email);

        System.out.println("Send mail complete");

    }
}
