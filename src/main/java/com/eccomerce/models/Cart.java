package com.eccomerce.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Michał on 10.01.2017.
 */
@Entity
@Table(name="checkouts")
public class Cart {
    public Cart() {
    this.productList = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Transient
    private List<Product> productList;

    @NotNull
    private String productsIdList;

    @NotNull
    private double priceForCart;

    @NotNull
    private String username;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public String getProductsIdList() {
        return productsIdList;
    }

    public void setProductsIdList(String productsIdList) {
        this.productsIdList = productsIdList;
    }

    public double getPriceForCart() {
        return priceForCart;
    }

    public void setPriceForCart(double priceForCart) {
        this.priceForCart = priceForCart;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void addToList(Product product){ this.productList.add(product);}

    @NotNull

    private Date date;

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", productList=" + productList +
                ", productsIdList='" + productsIdList + '\'' +
                ", priceForCart=" + priceForCart +
                ", username='" + username + '\'' +
                ", date=" + date +
                '}';
    }
}
