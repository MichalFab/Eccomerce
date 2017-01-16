package com.eccomerce.repositiories;

import com.eccomerce.models.Cart;
import com.eccomerce.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Michał on 11.01.2017.
 */
@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByUsername(String username);
}
