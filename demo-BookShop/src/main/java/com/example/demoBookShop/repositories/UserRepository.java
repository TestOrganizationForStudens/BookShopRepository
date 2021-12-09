package com.example.demoBookShop.repositories;

import com.example.demoBookShop.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByFirstName(String firstName);
    User findByLastName(String lastName);
    User findByUserName(String userName);
}
