package com.example.jonathandewitenterpriseapplications.service;

import com.example.jonathandewitenterpriseapplications.models.User;
import com.example.jonathandewitenterpriseapplications.models.UserDetail;

public interface IAccountService {

    public UserDetail create (UserDetail account);

    void createVerificationToken(UserDetail account, String token);

    void confirmAccount(String token);

    void deleteUser(String userName);
}
