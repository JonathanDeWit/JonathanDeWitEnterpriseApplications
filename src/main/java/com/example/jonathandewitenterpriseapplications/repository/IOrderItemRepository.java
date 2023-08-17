package com.example.jonathandewitenterpriseapplications.repository;

import com.example.jonathandewitenterpriseapplications.models.BasketItem;
import com.example.jonathandewitenterpriseapplications.models.OrderItem;
import org.springframework.data.repository.CrudRepository;

public interface IOrderItemRepository extends CrudRepository<OrderItem, Long> {
}
