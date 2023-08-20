package com.example.jonathandewitenterpriseapplications.repository;

import com.example.jonathandewitenterpriseapplications.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomProductRepository implements ICustomProductRepository {

    @Autowired
    private DataSource dataSource;

    @Override
    public Iterable<Product> findByPriceAndCategory(BigDecimal maxPrice, BigDecimal minPrice, String category, String productName) {
        JdbcTemplate template = new JdbcTemplate(dataSource);

        String sql = "select id, name, description, price, category, stock, img FROM products WHERE price BETWEEN ? AND ? ";


        List<Object> param = new ArrayList<>();
        param.add(minPrice);
        param.add(maxPrice);


        if (!category.equals("null")){
            sql += "AND category LIKE ?";
            param.add("%" + category + "%");
        }

        if (!productName.equals("null") && !productName.isBlank()){
            sql += "AND name LIKE ?";
            param.add("%" + productName + "%");
        }

        sql += ";";

        List<Product> products = template.query(sql, param.toArray(),
                (rs, rowNum) -> {
                    Product product = new Product();
                    product.setId(rs.getInt("id"));
                    product.setName(rs.getString("name"));
                    product.setDescription(rs.getString("description"));
                    product.setPrice(rs.getBigDecimal("price"));
                    product.setCategory(rs.getString("category"));
                    product.setStock(rs.getInt("stock"));
                    product.setImg(rs.getString("img"));
                    return product;
                }
        );

        return products;
    }
}
