package com.example.jonathandewitenterpriseapplications.repository;

import com.example.jonathandewitenterpriseapplications.models.BasketItem;
import com.example.jonathandewitenterpriseapplications.models.Order;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface IOrderRepository extends CrudRepository<Order, Long> {

    List<Order> findByUserUsername(String Username);

    @Modifying
    @Transactional
    @Query("DELETE FROM Order o WHERE o.user.username = :username")
    void deleteByUserUsername(@Param("username") String Username);


}
