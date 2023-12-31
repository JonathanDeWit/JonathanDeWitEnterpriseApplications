package com.example.jonathandewitenterpriseapplications.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface IBasketService {

    void saveToBasket(UserDetails userDetails, int productId, int quantity);

    void deleteFromBasket(int productId, String userName );

    void confirmPurchase(UserDetails user);

    void deleteUserBasketAndOrder(String userName);

}
