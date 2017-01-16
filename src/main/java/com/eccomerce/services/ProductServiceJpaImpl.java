package com.eccomerce.services;

import com.eccomerce.models.Product;
import com.eccomerce.repositiories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Michał on 01.01.2017.
 */

@Service
@Primary
public class ProductServiceJpaImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    @Override
    public List<Product> findByCategory(String category) {
        return this.productRepository.findByCategory(category);
    }


//    @Override
//    public List<Product> findLatest5() {
//        return null;
//    }

    @Override
    public Product findById(Long id) {
       return this.productRepository.findOne(id);
    }

    @Override
    public void addProduct(Product p) {
        this.productRepository.save(p);
    }
}
