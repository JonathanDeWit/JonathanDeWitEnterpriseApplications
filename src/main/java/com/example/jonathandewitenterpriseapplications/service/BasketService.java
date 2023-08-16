package com.example.jonathandewitenterpriseapplications.service;

import com.example.jonathandewitenterpriseapplications.repository.IBasketItemRepository;
import com.example.jonathandewitenterpriseapplications.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class BasketService implements IBasketService {
    @Autowired
    private IBasketItemRepository basketItemRepository;


    @Override
    public void saveToBasket(UserDetails userDetails, int productId, int quantity) {

    }
}
