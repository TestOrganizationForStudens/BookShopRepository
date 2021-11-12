package com.example.demoBookShop.repositories;

import com.example.demoBookShop.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
