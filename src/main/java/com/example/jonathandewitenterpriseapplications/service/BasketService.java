package com.example.jonathandewitenterpriseapplications.service;

import com.example.jonathandewitenterpriseapplications.models.*;
import com.example.jonathandewitenterpriseapplications.repository.IBasketItemRepository;
import com.example.jonathandewitenterpriseapplications.repository.IOrderItemRepository;
import com.example.jonathandewitenterpriseapplications.repository.IOrderRepository;
import com.example.jonathandewitenterpriseapplications.util.OnCreateOrderEvent;
import com.example.jonathandewitenterpriseapplications.util.OnCreteAccountEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class BasketService implements IBasketService {

    @Autowired
    private IOrderRepository orderRepository;

    @Autowired
    private IOrderItemRepository orderItemRepository;

    @Autowired
    private IBasketItemRepository basketItemRepository;

    @Autowired
    private ApplicationEventPublisher eventPublisher;


    @Override
    public void saveToBasket(UserDetails currentUser, int productId, int quantity) {

        var scan = basketItemRepository.findByUserNameAndProductId(productId, currentUser.getUsername());

        if (scan!=null){
            quantity+=scan.getQuantity();
            basketItemRepository.deleteByBasketId(scan.getId());
        }

        BasketItem basketItem = new BasketItem(new User(currentUser.getUsername(), currentUser.getPassword(), currentUser.isEnabled()), new Product(productId), quantity);
        basketItemRepository.save(basketItem);
    }

    @Override
//    @Transactional
    public void deleteFromBasket(int productId, String userName) {
        basketItemRepository.deleteByUserNameAndProductId(productId, userName);
    }

    @Override
    @Modifying
    @Transactional
    public void confirmPurchase(UserDetails userDetails) {
        var basketItems = basketItemRepository.findByUserUsername(userDetails.getUsername());
        var user = new User(userDetails.getUsername(), userDetails.getPassword(), userDetails.isEnabled());

        var order = new Order(user, LocalDateTime.now(), false);
        order= orderRepository.save(order);


        ArrayList<OrderItem> orderItems = new ArrayList<>();
        for (var item: basketItems) {
            orderItems.add(new OrderItem(user, item.getProduct(), order, item.getQuantity()));
        }
        orderItemRepository.saveAll(orderItems);

        var basketIds = basketItems.stream()
                .map(BasketItem::getId)
                .collect(Collectors.toList());
        basketItemRepository.deleteByBasketIds(basketIds);

        //send Email
        eventPublisher.publishEvent(new OnCreateOrderEvent(user, orderItems));
    }


}
