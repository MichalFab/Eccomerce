package com.eccomerce.repositiories;

import com.eccomerce.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by Michał on 02.01.2017.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public User findByUsername(String username);
//do skończenia aktualizowanie usera
//    @Query("update U")
//    public void update(User u);
}
