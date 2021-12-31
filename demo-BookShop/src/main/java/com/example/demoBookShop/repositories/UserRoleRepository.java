package com.example.demoBookShop.repositories;

import com.example.demoBookShop.models.User;
import com.example.demoBookShop.models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    Optional<UserRole> findByUserDt(User user);
}
