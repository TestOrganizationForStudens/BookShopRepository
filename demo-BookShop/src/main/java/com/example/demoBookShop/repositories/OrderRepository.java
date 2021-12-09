package com.example.demoBookShop.repositories;

import com.example.demoBookShop.models.Order;
import com.example.demoBookShop.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByDateTime(LocalDateTime dateTime);
    List<Order> findByUserData(User userData);
    List<Order> findByPrice(Double price);
}
