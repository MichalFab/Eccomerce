package com.eccomerce.services;

import com.eccomerce.models.Product;

import java.util.List;

/**
 * Created by Michał on 31.12.2016.
 */
public interface ProductService {
    List<Product> findAll();
   List<Product> findByCategory(String category);

   // List<Product> findLatest5();
    Product findById(Long id);
    void addProduct(Product p);



}
