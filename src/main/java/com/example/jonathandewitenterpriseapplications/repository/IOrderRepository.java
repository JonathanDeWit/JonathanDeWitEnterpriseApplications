package com.example.jonathandewitenterpriseapplications.repository;

import com.example.jonathandewitenterpriseapplications.models.BasketItem;
import com.example.jonathandewitenterpriseapplications.models.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface IOrderRepository extends CrudRepository<Order, Long> {

    List<Order> findByUserUsername(String Username);
}
