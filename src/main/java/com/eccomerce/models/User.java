package com.eccomerce.models;

import org.springframework.web.bind.annotation.ModelAttribute;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Michał on 31.12.2016.
 */

@Entity
@Table(name = "users")


public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "name")
    private String fullName;
    @Column(name = "street")
    private String street;
    @Column(name = "postcode")
    private String postCode;
    @Column(name = "city")
    private String city;
    @Column(name="role")
    private int role;

    public User() {
    }

    public User(Long id, String username, String password, String email, String fullName, String street, String postCode, String city, int role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
        this.street = street;
        this.postCode = postCode;
        this.city = city;
        this.role=role;
    }

//    public User(String username, String password, int role){
//        this.setUsername(username);
//        this.setpassword(password);
//        this.setRole(role);
//    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getpassword() {
        return password;
    }

    public void setpassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", fullName='" + fullName + '\'' +
                ", street='" + street + '\'' +
                ", postCode='" + postCode + '\'' +
                ", city='" + city + '\'' +
                ", role=" + role +
                '}';
    }
}
