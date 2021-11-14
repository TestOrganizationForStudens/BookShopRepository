package com.example.demoBookShop.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="products")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_product",
            updatable = false)
    @Setter
    @Getter
    private Long id;

    @Column(name="product_name",
            nullable = false,
            columnDefinition = "VARCHAR(100)")
    @Setter
    @Getter
    private String productName;

    @Column(name="category",
            nullable = false,
            columnDefinition = "VARCHAR(40)")
    @Setter
    @Getter
    private String category;

    @Column(name="author",
            nullable = false,
            columnDefinition = "VARCHAR(60)")
    @Setter
    @Getter
    private String author;

    @Column(name="publishingHouse",
            nullable = false,
            columnDefinition = "VARCHAR(40)")
    @Setter
    @Getter
    private String publishingHouse;

    @Column(name="year",
            nullable = false,
            columnDefinition = "INT")
    @Setter
    @Getter
    private Integer year;

    @Column(name="price",
            nullable = false,
            columnDefinition = "DOUBLE")
    @Setter
    @Getter
    private Double price;

    @Column(name="description",
            nullable = false,
            columnDefinition = "TEXT")
    @Setter
    @Getter
    private String description;

    @Column(name="image",
            nullable = false,
            columnDefinition = "TEXT")
    @Setter
    @Getter
    private String image;

    @JsonIgnore
    @OneToMany(mappedBy = "product")//, fetch=FetchType.LAZY, cascade=CascadeType.PERSIST)
    @Setter
    @Getter
    private List<ProductOrder> productOrdersList;

    public Product() {}

    public Product(String productName,
                   String category,
                   String author,
                   String publishingHouse,
                   Integer year,
                   Double price,
                   String description,
                   String image) {
        this.productName = productName;
        this.category = category;
        this.author = author;
        this.publishingHouse = publishingHouse;
        this.year = year;
        this.price = price;
        this.description = description;
        this.image = image;
    }

    public Product(Long id,
                   String productName,
                   String category,
                   String author,
                   String publishingHouse,
                   Integer year,
                   Double price,
                   String description,
                   String image) {
        this.id=id;
        this.productName = productName;
        this.category = category;
        this.author = author;
        this.publishingHouse = publishingHouse;
        this.year = year;
        this.price = price;
        this.description = description;
        this.image = image;
    }
    public Product(Long id,
                   String productName,
                   String category,
                   String author,
                   String publishingHouse,
                   Integer year,
                   Double price,
                   String description,
                   String image,
                   List<ProductOrder> productOrdersList) {
        this.id = id;
        this.productName = productName;
        this.category = category;
        this.author = author;
        this.publishingHouse = publishingHouse;
        this.year = year;
        this.price = price;
        this.description = description;
        this.image = image;
        this.productOrdersList = productOrdersList;
    }

    @Override
    public String toString() {
        return "Product{" +
                    "id=" + id +
                    ", productName='" + productName + '\'' +
                    ", category='" + category + '\'' +
                    ", author='" + author + '\'' +
                    ", publishingHouse='" + publishingHouse + '\'' +
                    ", year=" + year +
                    ", price=" + price +
                    ", description='" + description + '\'' +
                    ", image='" + image + '\'' +
                    '}';
    }
}

