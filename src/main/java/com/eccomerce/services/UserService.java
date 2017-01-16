package com.eccomerce.services;

import com.eccomerce.models.User;

import java.util.List;

/**
 * Created by Michał on 03.01.2017.
 */
public interface UserService {
    List<User> findAll();
    User findOneByUsername(String username);
    void save(User u);
    User findOneById(Long id);
//    void update(User user);
}
