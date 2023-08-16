package com.example.jonathandewitenterpriseapplications.controller;


import com.example.jonathandewitenterpriseapplications.models.BasketItem;
import com.example.jonathandewitenterpriseapplications.models.Product;
import com.example.jonathandewitenterpriseapplications.models.User;
import com.example.jonathandewitenterpriseapplications.repository.IBasketItemRepository;
import com.example.jonathandewitenterpriseapplications.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/basket")
public class BasketController {

    @Autowired
    private IBasketItemRepository basketItemRepository;

    @GetMapping(value = "/checkout")
    public String getBasket() {
        return "pages/basket";
    }

    @PostMapping(value = "/add")
    public String addProduct(@AuthenticationPrincipal UserDetails currentUser, int product_id, int quantity) {

//        BasketItem basketItem = new BasketItem(new User(currentUser.getUsername(),), new Product(product_id), quantity);

//        basketItemRepository.save();

        return "redirect:/products/catalogue";
    }


}
