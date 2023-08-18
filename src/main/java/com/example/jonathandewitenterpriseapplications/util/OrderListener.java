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

        User user = event.getUser();
        var orderItems = event.getOrderItems();

        // Calculate the total price of the order
        double totalPrice = orderItems.stream()
                .mapToDouble(item -> item.getProduct().getPrice().doubleValue() * item.getQuantity())
                .sum();

        // Set all email properties
        String recipientAddress = user.getUsername();
        String subject = "Ventistore Order";
        String message = "Than you for your order\nYour order includes:\n";

        for (var item:orderItems) {
            message += "\n- "+ item.getQuantity() +" . " + item.getProduct().getName() + " " + item.getProduct().getPrice();
        }

        message += "\n\nThe total price of the order is: "+totalPrice+"€";

        // Making email
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message);

        // Send mail
        mailSender.send(email);

        System.out.println("Send mail complete");
    }
}
