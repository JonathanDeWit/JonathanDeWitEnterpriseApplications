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

        // Load a potential basket item with the productId
        var scan = basketItemRepository.findByUserNameAndProductId(productId, currentUser.getUsername());

        // Check if the basket item is found
        if (scan!=null){
            // update the quantity of the basket item
            scan.setQuantity(scan.getQuantity()+quantity);
            basketItemRepository.save(scan);
        }
        else{
            // Save a new basket item
            BasketItem basketItem = new BasketItem(new User(currentUser.getUsername(), currentUser.getPassword(), currentUser.isEnabled()), new Product(productId), quantity);
            basketItemRepository.save(basketItem);
        }
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

        // Load all user basket items
        var basketItems = basketItemRepository.findByUserUsername(userDetails.getUsername());

        var user = new User(userDetails.getUsername(), userDetails.getPassword(), userDetails.isEnabled());

        // Make and save new order
        var order = new Order(user, LocalDateTime.now(), false);
        order= orderRepository.save(order);

        // Make an Order Item for every basket item
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        for (var item: basketItems) {
            orderItems.add(new OrderItem(user, item.getProduct(), order, item.getQuantity()));
        }
        // Save the order items
        orderItemRepository.saveAll(orderItems);

        // Select all the basketItemsIds
        var basketIds = basketItems.stream()
                .map(BasketItem::getId)
                .collect(Collectors.toList());
        // Delete all the corresponding basket items
        basketItemRepository.deleteByBasketIds(basketIds);

        // Send order confirmation e-mail
        eventPublisher.publishEvent(new OnCreateOrderEvent(user, orderItems));
    }

    @Override
    public void deleteUserBasketAndOrder(String userName) {

        // Delete user basket
        basketItemRepository.deleteByUserUsername(userName);

        // Delete user order items
        orderItemRepository.deleteByUserUsername(userName);

        // Delete user order
        orderRepository.deleteByUserUsername(userName);
    }


}
