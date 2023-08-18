package com.example.jonathandewitenterpriseapplications.controller;


import com.example.jonathandewitenterpriseapplications.models.BasketItem;
import com.example.jonathandewitenterpriseapplications.models.Product;
import com.example.jonathandewitenterpriseapplications.models.User;
import com.example.jonathandewitenterpriseapplications.repository.IBasketItemRepository;
import com.example.jonathandewitenterpriseapplications.repository.IProductRepository;
import com.example.jonathandewitenterpriseapplications.service.IAccountService;
import com.example.jonathandewitenterpriseapplications.service.IBasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.transaction.Transactional;

@Controller
@RequestMapping(value = "/basket")
public class BasketController {

    @Autowired
    private IBasketItemRepository basketItemRepository;

    @Autowired
    private IBasketService basketService;


    @GetMapping(value = "/checkout")
    public String getBasket(@AuthenticationPrincipal UserDetails currentUser, Model model) {

        // Load current user Basket Items
        var basket = basketItemRepository.findByUserUsername(currentUser.getUsername());

        // Calculate the total price of the items in the basket
        double totalPrice = basket.stream()
                .mapToDouble(item -> item.getProduct().getPrice().doubleValue() * item.getQuantity())
                .sum();

        model.addAttribute("basket", basket);
        model.addAttribute("totalPrice", totalPrice);

        return "pages/basket";
    }

    @PostMapping(value = "/add")
    public String addProduct(@AuthenticationPrincipal UserDetails currentUser, int product_id, int quantity) {

        // Add the product to the user basket
        basketService.saveToBasket(currentUser, product_id, quantity);

        return "redirect:/products/catalogue";
    }


    @PostMapping(value = "/delete")
    public String deleteProduct(@AuthenticationPrincipal UserDetails currentUser, int product_id) {

        // Delete product from the user basket
        basketService.deleteFromBasket(product_id, currentUser.getUsername());

        return "redirect:checkout";
    }

    @PostMapping(value = "/confirm_purchase")
    public String getConfirm_purchase(@AuthenticationPrincipal UserDetails currentUser) {

        // Transform basket into an order and save it
        basketService.confirmPurchase(currentUser);

        return "redirect:checkout";
    }




}
