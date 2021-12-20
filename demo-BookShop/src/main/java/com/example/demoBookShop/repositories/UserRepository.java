package com.example.demoBookShop.repositories;

import com.example.demoBookShop.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    User findByFirstName(String firstName);
    User findByLastName(String lastName);
    Optional<User> findByUserName(String userName);
}
