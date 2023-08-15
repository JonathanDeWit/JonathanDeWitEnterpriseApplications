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
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUsername(user.getUsername());

        verificationTokenRepository.save(verificationToken);
    }

    @Override
    public void confirmAccount(String token) {
        //retrieve token
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token);
        //verify date
        if(verificationToken.getExpiryDate().after(new Date())) {
            //move from account table to userdetails table
            UserDetail userDetail = userDetailRepository.findByUsername(verificationToken.getUsername());

            User user = new User(userDetail.getUsername(), userDetail.getPassword(), true);
            Authority authority = new Authority("ROLE_USER", user);

            userRepository.save(user);
            authorityRepository.save(authority);

            //delete from tokens
            verificationTokenRepository.deleteToken(token);
        }
    }

    @Override
    @Transactional
    public void deleteUser(String userName){

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
