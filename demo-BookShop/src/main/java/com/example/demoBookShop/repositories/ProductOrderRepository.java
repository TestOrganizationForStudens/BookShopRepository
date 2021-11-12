package com.example.demoBookShop.repositories;

import com.example.demoBookShop.models.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long> {
}
