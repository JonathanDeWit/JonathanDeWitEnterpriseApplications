package com.example.jonathandewitenterpriseapplications.controller;

import com.example.jonathandewitenterpriseapplications.repository.ICustomProductRepository;
import com.example.jonathandewitenterpriseapplications.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private ICustomProductRepository customProductRepository;

    @GetMapping("catalogue")
    public String getProducts(@RequestParam(value = "maxPrice", defaultValue = "100000") BigDecimal maxPrice,
                              @RequestParam(value = "minPrice", defaultValue = "0") BigDecimal minPrice,
                              @RequestParam(value = "category", defaultValue = "null") String category,
                              @RequestParam(value = "productName", defaultValue = "null") String productName,
                              Model model) {

        // Load all different categories type
        List<String> categories = productRepository.findDistinctCategories();

        // Load all the products matching the potential filters
        var productFilter = customProductRepository.findByPriceAndCategory(maxPrice, minPrice, category, productName);

        model.addAttribute("products", productFilter);
        model.addAttribute("categories", categories);

        return "pages/product/catalogue";
    }


    @GetMapping("product/{id}")
    public String getProduct(@PathVariable(value = "id") int id, Model model) {

        // Load a specific product
        var product = productRepository.findById(id);

        model.addAttribute("product", product.get());
        return "pages/product/product";
    }
}
