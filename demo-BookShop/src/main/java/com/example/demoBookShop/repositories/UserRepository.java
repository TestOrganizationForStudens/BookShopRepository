package com.example.demoBookShop.repositories;

import com.example.demoBookShop.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
