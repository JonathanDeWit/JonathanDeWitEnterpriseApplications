package com.example.jonathandewitenterpriseapplications.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {


    @GetMapping(value = "/")
    public String getRegistration() {
        return "redirect:home";
    }

    @GetMapping("/home")
    public String getHome() {
        return "pages/home";
    }

    @GetMapping("/about")
    public String getAbout() {
        return "pages/about";
    }
}
