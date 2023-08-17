package com.example.jonathandewitenterpriseapplications.util;

import com.example.jonathandewitenterpriseapplications.models.OrderItem;
import com.example.jonathandewitenterpriseapplications.models.User;
import org.springframework.context.ApplicationEvent;

import java.util.ArrayList;

public class OnCreateOrderEvent extends ApplicationEvent {

    private User user;
    private ArrayList<OrderItem> orderItems;

    public OnCreateOrderEvent(User user, ArrayList<OrderItem> orderItems) {
        super(user);
        this.user = user;
        this.orderItems = orderItems;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(ArrayList<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
