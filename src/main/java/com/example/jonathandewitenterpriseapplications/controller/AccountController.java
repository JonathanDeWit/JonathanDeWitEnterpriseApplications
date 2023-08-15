package com.example.jonathandewitenterpriseapplications.controller;

import com.example.jonathandewitenterpriseapplications.models.SecurityUser;
import com.example.jonathandewitenterpriseapplications.models.TestObject;
import com.example.jonathandewitenterpriseapplications.models.User;
import com.example.jonathandewitenterpriseapplications.models.UserDetail;
import com.example.jonathandewitenterpriseapplications.repository.IUserDetailRepository;
import com.example.jonathandewitenterpriseapplications.service.IAccountService;
import com.example.jonathandewitenterpriseapplications.util.OnCreteAccountEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.Registration;
import javax.validation.Valid;

@Controller
@RequestMapping(value = "/account")
public class AccountController {

    @Autowired
    private IAccountService accountService;

    @Autowired
    private IUserDetailRepository userDetailRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private ApplicationEventPublisher eventPublisher;


    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password are invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "pages/account/login";
    }

    @GetMapping(value = "/regist")
    public String getRegistration(Model model) {
        model.addAttribute("userDetail", new UserDetail());
        return "pages/account/regist";
    }

    @PostMapping(value = "/regist")
    public String addRegistration(
            @Valid UserDetail account,
            BindingResult result) {

        System.out.println("Test");
        //encrypt password
        account.setPassword(encoder.encode(account.getPassword()));

        //create the account
        account = accountService.create(account);

        //fire off an event on creation
        eventPublisher.publishEvent(new OnCreteAccountEvent(account));

        return "redirect:login";
    }

    @GetMapping("accountConfirm")
    public String confirmAccount(@RequestParam("token") String token) {
        accountService.confirmAccount(token);

        return "pages/account/account_confirmed";
    }


    @GetMapping("/profile")
    public String showUserProfile(@AuthenticationPrincipal UserDetails currentUser, Model model) {
        UserDetail userDetail = userDetailRepository.findByUsername(currentUser.getUsername());

        model.addAttribute("userDetail", userDetail);
        return "pages/account/profile";
    }

    @PostMapping(value = "/delete")
    public String deleteUser(@AuthenticationPrincipal UserDetails currentUser) {

        accountService.deleteUser(currentUser.getUsername());
        return "redirect:/perform_logout";
    }


}
