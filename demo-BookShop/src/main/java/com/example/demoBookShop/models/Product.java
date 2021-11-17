package com.example.demoBookShop.models;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="products")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_product",
            updatable = false)
    private Long id;

    @Column(name="product_name",
            nullable = false,
            columnDefinition = "VARCHAR(100)")
    private String productName;

    @Column(name="category",
            nullable = false,
            columnDefinition = "VARCHAR(40)")
    private String category;

    @Column(name="author",
            nullable = false,
            columnDefinition = "VARCHAR(60)")
    private String author;

    @Column(name="publishingHouse",
            nullable = false,
            columnDefinition = "VARCHAR(40)")
    private String publishingHouse;

    @Column(name="year",
            nullable = false,
            columnDefinition = "INT")
    private Integer year;

    @Column(name="price",
            nullable = false,
            columnDefinition = "DOUBLE")
    private Double price;

    @Column(name="description",
            nullable = false,
            columnDefinition = "TEXT")
    private String description;

    @Column(name="image",
            nullable = false,
            columnDefinition = "TEXT")
    private String image;

    @Column(name="inStore",
            nullable = false,
            columnDefinition = "INT")
    private Integer inStore;

    @OneToMany(mappedBy = "product")//, fetch=FetchType.LAZY, cascade=CascadeType.PERSIST)
    @JsonIgnore
    private List<ProductOrder> productOrdersList;

    public Product() {}

    public Product(String productName,
                   String category,
                   String author,
                   String publishingHouse,
                   Integer year,
                   Double price,
                   String description,
                   Integer inStore,
                   String image) {
        this.productName = productName;
        this.category = category;
        this.author = author;
        this.inStore=inStore;
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
                   Integer inStore,
                   String image) {
        this.id=id;
        this.productName = productName;
        this.category = category;
        this.author = author;
        this.inStore=inStore;
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
                   Integer inStore,
                   List<ProductOrder> productOrdersList) {
        this.id = id;
        this.productName = productName;
        this.inStore=inStore;
        this.category = category;
        this.author = author;
        this.publishingHouse = publishingHouse;
        this.year = year;
        this.price = price;
        this.description = description;
        this.image = image;
        this.productOrdersList = productOrdersList;
    }

    public int getInStore() {
        return inStore;
    }

    public void setInStore(int inStore) {
        this.inStore = inStore;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<ProductOrder> getProductOrdersList() {
        return productOrdersList;
    }

    public void setProductOrdersList(List<ProductOrder> productOrdersList) {
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

