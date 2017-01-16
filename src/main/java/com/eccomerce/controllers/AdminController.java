package com.eccomerce.controllers;

import com.eccomerce.models.Cart;
import com.eccomerce.models.Product;
import com.eccomerce.models.User;
import com.eccomerce.services.CartServiceJpaImp;
import com.eccomerce.services.ProductServiceJpaImpl;
import com.eccomerce.services.UserServiceJpaImpl;
import org.hibernate.annotations.Check;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Michał on 05.01.2017.
 */
@Controller
public class AdminController {

    @Autowired
    private UserServiceJpaImpl userServiceJpa;
    @Autowired
    private ProductServiceJpaImpl productServiceJpa;
    @Autowired
    private CartServiceJpaImp cartServiceJpaImp;

    public static final String uploadingdir = System.getProperty("user.dir") + "/uploadingdir/";

    @RequestMapping("/admin")
    public String adminHome(Model model){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        User user = userServiceJpa.findOneByUsername(username);
        model.addAttribute("user", user);
        return "/admin/index";
    }
    @RequestMapping("/admin/add")
    public String adminAddProductForm(Model model){
        Product product  = new Product();
        model.addAttribute("product", product);
        return "/admin/add";
    }
    @RequestMapping(value = "/admin/add",method = RequestMethod.POST)
//    public String adminAddProductSubmit(final @ModelAttribute(value = "product") Product product,
//                                       final @RequestAttribute(value = "image") MultipartFile uploadingFile){

   public String adminAddProductSubmit(@ModelAttribute(value = "product") Product product){
//        File file = new File(uploadingdir + uploadingFile.getOriginalFilename());
//
//        try {
//            uploadingFile.transferTo(file);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        //funkcjonalność zdjęć - do skończenia
      product.setImage("zdjecie.jpg");
       productServiceJpa.addProduct(product);
        return "/admin/added";
    }

    @RequestMapping("/admin/orders")
    public String ordersView(Model model){
        List<Cart> checkouts = cartServiceJpaImp.findAll();
        List<CheckoutsData> checkoutsData = new ArrayList<>();
        for(Cart allCheckouts : checkouts){
            String adress = "";
            String [] split = allCheckouts.getProductsIdList().split(";");
            List<String> productsList = new ArrayList<>();
            for(String productID : split){
                Product product = productServiceJpa.findById(Long.valueOf(productID));
                productsList.add(product.getTitle());
            }
            adress=userServiceJpa.findOneByUsername(allCheckouts.getUsername()).getFullName()
                    + ", " + userServiceJpa.findOneByUsername(allCheckouts.getUsername()).getStreet()
                    + ", " + userServiceJpa.findOneByUsername(allCheckouts.getUsername()).getPostCode()
                    + ", " + userServiceJpa.findOneByUsername(allCheckouts.getUsername()).getCity();
            checkoutsData.add(new CheckoutsData(allCheckouts.getUsername(),productsList,allCheckouts.getPriceForCart(),
                    allCheckouts.getDate(),adress));
        }
//        for(CheckoutsData test : checkoutsData){
//            System.out.println(test.getUsername() + " kupił : ");
//            for(String s : test.getProducts()){
//                System.out.print(s + " ");
//            }
//            System.out.println("Za cenę: " + test.getPrice());
//        }

        model.addAttribute("checkouts", checkoutsData);
        return "/admin/orders";
    }

    private static class CheckoutsData{

        public CheckoutsData(String username, List<String> products, double price, Date date, String address) {
            this.username = username;
            this.products = products;
            this.price = price;
            this.date = date;
            this.address = address;
        }

        public String getUsername() {
            return username;
        }

        public List<String> getProducts() {
            return products;
        }

        public double getPrice() {
            return price;
        }

        public Date getDate() {
            return date;
        }

        private final String username;
        private final  List<String> products;
        private final double price;
        private final String address;

        public String getAddress() {
            return address;
        }

        private final Date date;

        @Override
        public String toString() {
            return "CheckoutsData{" +
                    "username='" + username + '\'' +
                    ", products=" + products +
                    ", price=" + price +
                    ", date=" + date +
                    '}';
        }
    }

}
