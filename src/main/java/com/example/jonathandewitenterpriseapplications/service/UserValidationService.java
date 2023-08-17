package com.example.jonathandewitenterpriseapplications.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidationService {
    public static boolean isValidEmail(String email) {
        // Define a regular expression pattern for basic email validation
        String emailPattern = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";

        // Compile the pattern
        Pattern pattern = Pattern.compile(emailPattern);

        // Match the email against the pattern
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }
}