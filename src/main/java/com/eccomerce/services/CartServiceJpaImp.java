package com.eccomerce.services;

import com.eccomerce.models.Cart;
import com.eccomerce.models.User;
import com.eccomerce.repositiories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Michał on 11.01.2017.
 */

@Service
@Primary
public class CartServiceJpaImp implements CartService {

    @Autowired
    CartRepository cartRepository;

    @Override
    public List<Cart> findAll() {
        return this.cartRepository.findAll();
    }

    @Override
    public void finishCheckout(Cart c) {
        cartRepository.save(c);
    }

    @Override
    public List<Cart> findbyUsername(String username) {
        return cartRepository.findByUsername(username);
    }
}
