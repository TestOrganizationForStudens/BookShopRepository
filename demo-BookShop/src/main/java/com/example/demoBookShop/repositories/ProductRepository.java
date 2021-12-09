package com.example.demoBookShop.repositories;

import com.example.demoBookShop.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.Entity;
import java.util.List;

import static org.hibernate.loader.Loader.SELECT;
import static org.springframework.http.HttpHeaders.FROM;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByAuthor(String author);
    List<Product> findByCategory(String category);
    List<Product> findByProductName(String productName);
    List<Product> findByYear(Integer year);
    List<Product> findByPublishingHouse(String publishHouse);
    List<Product> findByPrice(Double price);

    @Query("FROM Product prod WHERE prod.price< ?1")
    List<Product> findByPriceThatAreCheaper(Double price);

    @Query("FROM Product prod WHERE prod.price> ?1")
    List<Product> findByPriceThatAreExpensive(Double price);
}
