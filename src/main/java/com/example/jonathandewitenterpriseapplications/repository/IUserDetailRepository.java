package com.example.jonathandewitenterpriseapplications.repository;

import com.example.jonathandewitenterpriseapplications.models.UserDetail;
import org.springframework.data.repository.CrudRepository;

public interface IUserDetailRepository extends CrudRepository<UserDetail, String> {
    public UserDetail findByUsername(String username);
}
