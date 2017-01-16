package com.eccomerce.services;

import com.eccomerce.models.User;
import com.eccomerce.repositiories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Michał on 03.01.2017.
 */
@Service
@Primary
public class UserServiceJpaImpl implements UserService {
  @Autowired
  private UserRepository userRepository;
    @Override
    public List<User> findAll() {
     return this.userRepository.findAll();
    }

    @Override
    public User findOneByUsername(String username) {
    return this.userRepository.findByUsername(username);
    }

  @Override
  public void save(User u) {
    userRepository.save(u);
  }

  @Override
    public User findOneById(Long id) {
        return this.userRepository.findOne(id);
    }


//  @Override
//  public void update(User user) {
//    this.userRepository.
//  }
}
