package com.example.jonathandewitenterpriseapplications.repository;

import com.example.jonathandewitenterpriseapplications.models.BasketItem;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface IBasketItemRepository extends CrudRepository<BasketItem, Long> {

    List<BasketItem> findByUserUsername(String Username);

    @Query("SELECT b FROM BasketItem b WHERE b.product.id = :productId and b.user.username = :username")
    BasketItem findByUserNameAndProductId(@Param("productId") int productId, @Param("username") String username);

    @Modifying
    @Transactional
    @Query("delete FROM BasketItem b WHERE b.product.id = :productId and b.user.username = :username")
    int deleteByUserNameAndProductId(@Param("productId") int productId, @Param("username") String username);

    @Modifying
    @Transactional
    @Query("delete FROM BasketItem b WHERE b.id = :basketId")
    void deleteByBasketId(long basketId);

    @Modifying
    @Transactional
    @Query("DELETE FROM BasketItem b WHERE b.id IN :basketIds")
    void deleteByBasketIds(List<Long> basketIds);


}
