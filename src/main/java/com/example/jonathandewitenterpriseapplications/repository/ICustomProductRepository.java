package com.example.jonathandewitenterpriseapplications.repository;

import com.example.jonathandewitenterpriseapplications.models.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ICustomProductRepository {

    Iterable<Product> findByPriceAndCategory(BigDecimal maxPrice, BigDecimal minPrice, String category);
}
