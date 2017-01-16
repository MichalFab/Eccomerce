package com.eccomerce.controllers;

import com.eccomerce.models.Cart;
import com.eccomerce.models.Product;
import com.eccomerce.services.CartServiceJpaImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by Michał on 10.01.2017.
 */

@Controller
@RequestMapping(value="/checkout")
public class CheckoutController {
    @Autowired
    private CartServiceJpaImp cartServiceJpaImp;

    @RequestMapping(value="/")
    public String checkoutProducts(HttpServletRequest request, Model model){
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if(cart.getProductList().isEmpty()) {
            return "redirect:/cart/?text=Twoj koszyk jest pusty!";
        }
        String productsIdLists = "";
        for(Product product : cart.getProductList()){
            productsIdLists = productsIdLists + product.getId() + ";";
        }
        cart.setProductsIdList(productsIdLists);
        cart.setDate(new Date(System.currentTimeMillis()));
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        cart.setUsername(username);
        cartServiceJpaImp.finishCheckout(cart);

        List<Product> allProducts = cart.getProductList();
        double sum = cart.getPriceForCart();
        //System.out.println("Suma zamówienia: " + sum);
        model.addAttribute("cart",cart);
        model.addAttribute("allProducts",allProducts);
        model.addAttribute("totalPrice",  sum);


        return "/user/checkout";


    }
}
