package com.example.jonathandewitenterpriseapplications.service;

import com.example.jonathandewitenterpriseapplications.models.*;
import com.example.jonathandewitenterpriseapplications.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AccountService implements IAccountService {

    @Autowired
    private IBasketService basketService;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IAuthorityRepository authorityRepository;

    @Autowired
    private IUserDetailRepository userDetailRepository;

    @Autowired
    private IPersistentLoginRepository persistentLoginRepository;

    @Autowired
    private IVerificationTokenRepository verificationTokenRepository;


    @Override
    public UserDetail create(UserDetail userDetail) {
        return userDetailRepository.save(userDetail);
    }

    @Override
    public void createVerificationToken(UserDetail user, String token) {
        // Create and save new verification token
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUsername(user.getUsername());

        verificationTokenRepository.save(verificationToken);
    }

    @Override
    public void confirmAccount(String token) {
        // Retrieve token
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token);
        // Verify token date
        if(verificationToken.getExpiryDate().after(new Date())) {
            // Load user detail
            UserDetail userDetail = userDetailRepository.findByUsername(verificationToken.getUsername());

            // Make new user
            User user = new User(userDetail.getUsername(), userDetail.getPassword(), true);

            // Make new authority for the user
            Authority authority = new Authority("ROLE_USER", user);

            // Save user
            userRepository.save(user);

            //Save authority
            authorityRepository.save(authority);

            // Delete from verification token
            verificationTokenRepository.deleteToken(token);
        }
    }

    @Override
    @Transactional
    public void deleteUser(String userName){

        //Delete Basket
        basketService.deleteUserBasketAndOrder(userName);

        //delete token
        persistentLoginRepository.deleteByUsername(userName);

        //delete userdetails
        userDetailRepository.deleteById(userName);

        //delete authorities
        authorityRepository.deleteByUser_Username(userName);

        //delete user
        userRepository.deleteById(userName);
    }
}
