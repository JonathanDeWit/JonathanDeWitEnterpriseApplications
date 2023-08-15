package com.example.jonathandewitenterpriseapplications.repository;

import com.example.jonathandewitenterpriseapplications.models.PersistentLogin;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface IPersistentLoginRepository extends CrudRepository<PersistentLogin, String> {
//    @Modifying
//    @Transactional
//    @Query("delete from PersistentLogin p where p.username = ?1")
    void deleteByUsername(String username);
}
