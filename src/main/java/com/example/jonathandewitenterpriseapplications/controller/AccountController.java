package com.example.jonathandewitenterpriseapplications.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/account")
public class AccountController {

    @GetMapping(value = "/login")
    public String getLogin() {
        return "pages/account/login";
    }

    @GetMapping(value = "/regist")
    public String getRegistration() {
        return "pages/account/regist";
    }
}
