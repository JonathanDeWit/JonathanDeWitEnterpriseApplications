package com.example.jonathandewitenterpriseapplications.repository;

import com.example.jonathandewitenterpriseapplications.models.Authority;
import com.example.jonathandewitenterpriseapplications.models.BasketItem;
import com.example.jonathandewitenterpriseapplications.models.VerificationToken;
import org.springframework.data.repository.CrudRepository;

public interface IBasketItemRepository extends CrudRepository<BasketItem, Long> {

    BasketItem findByUserNameAnd(String username);

}
