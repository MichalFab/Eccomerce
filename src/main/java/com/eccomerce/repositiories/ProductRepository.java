package com.eccomerce.repositiories;

import com.eccomerce.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Michał on 01.01.2017.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

      List<Product> findByCategory(String category);
}
