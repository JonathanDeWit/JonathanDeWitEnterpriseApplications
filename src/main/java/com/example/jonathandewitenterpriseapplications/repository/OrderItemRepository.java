package com.example.jonathandewitenterpriseapplications.repository;

import com.example.jonathandewitenterpriseapplications.models.BasketItem;
import org.springframework.data.repository.CrudRepository;

public interface OrderItemRepository extends CrudRepository<BasketItem, Long> {
}
