package com.eccomerce.services;

import com.eccomerce.models.Cart;
import com.eccomerce.models.User;

import java.util.List;

/**
 * Created by Michał on 11.01.2017.
 */
public interface CartService {

   List<Cart> findAll();
   void finishCheckout(Cart c);
   List<Cart> findbyUsername(String username);
}
