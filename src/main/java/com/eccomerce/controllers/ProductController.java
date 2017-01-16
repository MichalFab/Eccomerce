package com.eccomerce.controllers;

import com.eccomerce.models.Product;
import com.eccomerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Michał on 04.01.2017.
 */

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping("/product/{id}")
    public String product(@PathVariable("id") Long id, Model model){
        Product product = productService.findById(id);
        model.addAttribute("product",product);
        return "/products/index";
    }


}
