package com.eccomerce.controllers;

import com.eccomerce.models.Cart;
import com.eccomerce.models.Product;
import com.eccomerce.models.User;
import com.eccomerce.services.ProductService;
import com.eccomerce.services.ProductServiceJpaImpl;
import com.eccomerce.services.UserService;
import com.eccomerce.services.UserServiceJpaImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Michał on 10.01.2017.
 */
@Controller
@RequestMapping(value="/cart/")
public class CartController {
    @Autowired
    private ProductServiceJpaImpl ProductService;
    @Autowired
    private UserServiceJpaImpl userServiceJpa;

    @RequestMapping("/")
    public String index(HttpServletRequest request, Model model){
        Cart cart = (Cart) request.getSession().getAttribute("cart");
       //test
       List<Product> products = cart.getProductList();
        for(Product p : products ) {
            System.out.println(p.getTitle());
        }
        model.addAttribute("cart",cart);
        model.addAttribute("allProducts",products);
        return "/cart/cart";
    }

    @RequestMapping("/add/{productId}")
    public String addToCart(@PathVariable("productId") long productId, Model model, HttpServletRequest request){
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        cart.setUsername(request.getUserPrincipal().getName());
        Product product  = ProductService.findById(productId);
        cart.addToList(product);
        cart.setPriceForCart(cart.getPriceForCart() + product.getPrice());

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        User user = userServiceJpa.findOneByUsername(username);

        model.addAttribute("user", user);
        model.addAttribute("product", product);
        return "/cart/addItem";
    }

    @RequestMapping(value="/remove/{productId}")
    public String delete(@PathVariable("productId") long productId, HttpServletRequest request){
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        List<Product> productList = cart.getProductList();
        Product product =ProductService.findById(productId);
        for(Product products : productList){
            if(productId == products.getId()){
                productList.remove(products);
                break;
            }
        }
        return "redirect:/cart/?text=Produkt+" + product.getTitle() + "+zostal pomyslnie usuniety";
    }

}
