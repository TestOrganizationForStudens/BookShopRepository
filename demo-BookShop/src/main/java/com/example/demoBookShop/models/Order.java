package com.example.demoBookShop.models;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="orders")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_order",
            updatable = false)
    private Long id;

    @Column(name="date_time",
            nullable = false,
            columnDefinition = "DATETIME")
    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "user_id",
            referencedColumnName = "id_user",
            nullable = false)
    User userData;

    @Column(name="price",
            nullable = false,
            columnDefinition = "DOUBLE")
    private Double price;

    @OneToMany(mappedBy = "order")//, fetch=FetchType.LAZY, cascade=CascadeType.PERSIST)
    private List<ProductOrder> productOrderList;

    public Order() {
    }

    public Order(LocalDateTime dateTime,
                 User user,
                 Double price) {
        this.dateTime = dateTime;
        this.price = price;
        this.userData=user;
    }

    public Order(Long id,
                 LocalDateTime dateTime,
                 User userData,
                 Double price,
                 List<ProductOrder> productOrderList) {
        this.id = id;
        this.dateTime = dateTime;
        this.userData = userData;
        this.price = price;
        this.productOrderList = productOrderList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public User getUserData() {
        return userData;
    }

    public void setUserData(User userData) {
        this.userData = userData;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<ProductOrder> getProductOrderList() {
        return productOrderList;
    }

    public void setProductOrderList(List<ProductOrder> productOrderList) {
        this.productOrderList = productOrderList;
    }

    @Override
    public String toString() {
        return "Odrer{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", price=" + price +
                '}';
    }
}
