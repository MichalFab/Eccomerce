package com.eccomerce.controllers;

import com.eccomerce.models.Cart;
import com.eccomerce.models.Product;
import com.eccomerce.models.User;
import com.eccomerce.services.CartServiceJpaImp;
import com.eccomerce.services.ProductService;
import com.eccomerce.services.UserServiceJpaImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Michał on 31.12.2016.
 */

@Controller
public class HomeController {

    @Autowired
    private ProductService productService;
    @Autowired
    private UserServiceJpaImpl userServiceJpa;
    @Autowired
    private CartServiceJpaImp cartServiceJpaImp;




    @RequestMapping("/")
    public String index(Model model){

        List<Product> allProducts = productService.findAll();
        model.addAttribute("allProducts",allProducts);

        return "index";
    }
    @RequestMapping("/login")
    public String login() {
        return "/login/login";
    }



    @RequestMapping("/onas")
    public String onas(){
        return "/info/about";
    }

    @RequestMapping("/krawaty")
    public String krawaty(Model model){
        List<Product> krawaty = productService.findByCategory("krawaty");

        model.addAttribute("krawaty",krawaty);
        return "/products/krawaty";
    }

    @RequestMapping("/koszule")
    public String koszule(Model model){
        List<Product> koszule = productService.findByCategory("koszule");

        model.addAttribute("koszule",koszule);
        return "/products/koszule";
    }
    @RequestMapping("/zegarki")
    public String zegarki(Model model){
        List<Product> zegarki = productService.findByCategory("zegarki");

        model.addAttribute("zegarki",zegarki);
        return "/products/zegarki";
    }

    @RequestMapping("/user")
    public String helloUser(Model model) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();

        User user = userServiceJpa.findOneByUsername(username);

        model.addAttribute("user", user);
        return "/user/index";
    }

    @RequestMapping(value = "/updateUser",method = RequestMethod.POST)
        public String updateUser(@ModelAttribute(value="user") User user){
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        String username = ((UserDetails) principal).getUsername();
//        user =userServiceJpa.findOneByUsername(username);
//        userServiceJpa.save(user);
        System.out.println(user);
        System.out.println(user.getFullName());
        System.out.println(user.getEmail());
        System.out.println(user.getPostCode());
        System.out.println(user.getCity());
        System.out.println(user.getStreet());
        return "/user/index";

    }

    @RequestMapping("/user/orders")
    public String myOrders(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();
            List<Cart> checkouts = (List<Cart>) cartServiceJpaImp.findbyUsername(username);
            List<OrderData> products = new ArrayList<OrderData>();

            for(Cart allCheckaouts : checkouts ){
                String  [] split = allCheckaouts.getProductsIdList().split(";");
                    for(String s: split){
                        Product p = productService.findById(Long.valueOf(s));
                        products.add(new OrderData(p.getTitle(), p.getPrice(), allCheckaouts.getDate()));

                }

                //System.out.println(allCheckaouts.getProductsIdList());
            }

        model.addAttribute("allProducts", products);
        return "/user/orders";
    }

    private static class OrderData{
            private final String title;
            private final double price;
            private final Date date;

        public OrderData(String title, double price, Date date) {
            this.title = title;
            this.price = price;
            this.date = date;
        }

        public String getTitle() {
            return title;
        }

        public double getPrice() {
            return price;
        }

        public Date getDate() {
            return date;
        }
    }



}
