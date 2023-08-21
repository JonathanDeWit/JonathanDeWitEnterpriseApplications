package com.example.jonathandewitenterpriseapplications.controller;

import com.example.jonathandewitenterpriseapplications.models.SecurityUser;
import com.example.jonathandewitenterpriseapplications.models.TestObject;
import com.example.jonathandewitenterpriseapplications.models.User;
import com.example.jonathandewitenterpriseapplications.models.UserDetail;
import com.example.jonathandewitenterpriseapplications.repository.IOrderRepository;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    private IOrderRepository orderRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private ApplicationEventPublisher eventPublisher;



    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        // Check for any authentication error messages
        if (error != null)
            model.addAttribute("error", "Your username and password are invalid.");

        // Check if user has been logged out
        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        // Confirmation message after account registration
        if (model.containsAttribute("confirmationMessage")) {
            model.addAttribute("confirmationMessage", model.getAttribute("confirmationMessage"));
        }

        return "pages/account/login";
    }

    // User registration
    @GetMapping(value = "/regist")
    public String getRegistration(Model model) {
        model.addAttribute("userDetail", new UserDetail());

        // Check for any error messages from the POST registration method
        if (model.containsAttribute("errorMessage")) {
            model.addAttribute("errorMessage", model.getAttribute("errorMessage"));
        }

        return "pages/account/regist";
    }

    @PostMapping(value = "/regist")
    public String addRegistration(
            @Valid UserDetail account,
            BindingResult result,
            RedirectAttributes redirectAttributes) {

        // Check for validation errors
        if (result.hasErrors()) {
            return "redirect:regist";
        }

        // Confirm that password and confirm password fields match
        if (!account.getPassword().equals(account.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("errorMessage", "Password field and confirm password should have the same value");

            return "redirect:regist";
        }

        // Encrypt password
        account.setPassword(encoder.encode(account.getPassword()));

        // Create the account
        account = accountService.create(account);

        // Fire create account event
        eventPublisher.publishEvent(new OnCreteAccountEvent(account));

        // Provide user with a message to check their email
        redirectAttributes.addFlashAttribute("confirmationMessage", "Please check your email to confirm your registration.");

        return "redirect:login";
    }

    @GetMapping("accountConfirm")
    public String confirmAccount(@RequestParam("token") String token) {
        accountService.confirmAccount(token);

        return "pages/account/account_confirmed";
    }


    @GetMapping("/profile")
    public String showUserProfile(@AuthenticationPrincipal UserDetails currentUser, Model model) {

        // Load user details
        UserDetail userDetail = userDetailRepository.findByUsername(currentUser.getUsername());

        // Load user orders
        var orders = orderRepository.findByUserUsername(currentUser.getUsername());

        model.addAttribute("userDetail", userDetail);
        model.addAttribute("orders", orders);
        return "pages/account/profile";
    }

    @PostMapping("/update")
    public String updateProfile(
            @AuthenticationPrincipal UserDetails currentUser,
            @RequestParam("firstname") String firstname,
            @RequestParam("lastname") String lastname,
            Model model) {

        // Load the user's details
        UserDetail userDetail = userDetailRepository.findByUsername(currentUser.getUsername());

        // Update the user's firstname and lastname
        userDetail.setFirstname(firstname);
        userDetail.setLastname(lastname);

        // Save the updated user details
        userDetailRepository.save(userDetail);

        return "redirect:/account/profile";
    }

    @PostMapping(value = "/delete")
    public String deleteUser(@AuthenticationPrincipal UserDetails currentUser) {

        // Delete user
        accountService.deleteUser(currentUser.getUsername());
        return "redirect:/perform_logout";
    }


}
