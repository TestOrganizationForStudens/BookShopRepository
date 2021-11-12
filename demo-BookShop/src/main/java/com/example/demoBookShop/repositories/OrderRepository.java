package com.example.demoBookShop.repositories;

import com.example.demoBookShop.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
