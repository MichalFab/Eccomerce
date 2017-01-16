package com.eccomerce.models;

import javax.persistence.*;

/**
 * Created by Michał on 31.12.2016.
 */

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 300)
    private String title;
    @Column(nullable = false, length = 150)
    private String category;
    @Column(nullable = false, length = 350)
    private String shortDesc;
    @Column(nullable = false, length = 2500)
    private String description;
    @Column(nullable = false)
    private int amount;
    @Column(nullable = false)
    private double price;
    @Column(nullable = false)
    private String image;

    public Product() {
    }

    public Product(Long id, String title, String category, String shortDesc, String description, int amount, double price, String image) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.shortDesc = shortDesc;
        this.description = description;
        this.amount = amount;
        this.price = price;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", category='" + category + '\'' +
                ", shortDesc='" + shortDesc + '\'' +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", price=" + price +
                ", image='" + image + '\'' +
                '}';
    }
}
