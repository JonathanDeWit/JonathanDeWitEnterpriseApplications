package com.example.jonathandewitenterpriseapplications.repository;

import com.example.jonathandewitenterpriseapplications.models.Product;

import java.math.BigDecimal;

public interface ICustomProductRepository {

    Iterable<Product> findByFilter(BigDecimal maxPrice, BigDecimal minPrice, String category, String productName, String sortBy);
}
