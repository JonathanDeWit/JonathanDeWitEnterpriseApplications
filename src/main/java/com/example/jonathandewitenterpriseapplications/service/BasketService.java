package com.example.jonathandewitenterpriseapplications.service;

import com.example.jonathandewitenterpriseapplications.models.BasketItem;
import com.example.jonathandewitenterpriseapplications.models.Product;
import com.example.jonathandewitenterpriseapplications.models.User;
import com.example.jonathandewitenterpriseapplications.repository.IBasketItemRepository;
import com.example.jonathandewitenterpriseapplications.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BasketService implements IBasketService {
    @Autowired
    private IBasketItemRepository basketItemRepository;


    @Override
    public void saveToBasket(UserDetails currentUser, int productId, int quantity) {

        var scan = basketItemRepository.findByUserNameAndProductId(productId, currentUser.getUsername());

        if (scan!=null){
            quantity+=scan.getQuantity();
            basketItemRepository.deleteById(scan.getId());
        }

        BasketItem basketItem = new BasketItem(new User(currentUser.getUsername(), currentUser.getPassword(), currentUser.isEnabled()), new Product(productId), quantity);
        basketItemRepository.save(basketItem);
    }

    @Override
    @Transactional
    public void deleteFromBasket(long basketItemId) {

        basketItemRepository.deleteById(basketItemId);

    }

}
