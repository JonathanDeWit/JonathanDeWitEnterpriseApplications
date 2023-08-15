package com.example.jonathandewitenterpriseapplications.repository;

import com.example.jonathandewitenterpriseapplications.models.Authority;
import com.example.jonathandewitenterpriseapplications.models.User;
import org.springframework.data.repository.CrudRepository;

public interface IAuthorityRepository extends CrudRepository<Authority, Long> {
    void deleteByUser_Username(String username);
}
