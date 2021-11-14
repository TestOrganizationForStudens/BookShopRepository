package com.example.demoBookShop.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="orders")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_order",
            updatable = false)
    @Setter
    @Getter
    private Long id;

    @Column(name="date_time",
            nullable = false,
            columnDefinition = "DATETIME")
    @Setter
    @Getter
    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "user_id",
            referencedColumnName = "id_user",
            nullable = false)
    @Setter
    @Getter
    User userData;

    @Column(name="price",
            nullable = false,
            columnDefinition = "DOUBLE")
    @Setter
    @Getter
    private Double price;

    @JsonIgnore
    @OneToMany(mappedBy = "order")//, fetch=FetchType.LAZY, cascade=CascadeType.PERSIST)
    @Setter
    @Getter
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

    @Override
    public String toString() {
        return "Odrer{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", price=" + price +
                '}';
    }
}
