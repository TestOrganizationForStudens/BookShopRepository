package com.example.demoBookShop.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="orders")
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

    @Column(name="id_user",
            nullable = false,
            columnDefinition = "BIGINT")
    @Setter
    @Getter
    private Long userId;

    @Column(name="price",
            nullable = false,
            columnDefinition = "DOUBLE")
    @Setter
    @Getter
    private Double price;

    @OneToMany(mappedBy = "order")//, fetch=FetchType.LAZY, cascade=CascadeType.PERSIST)
    @Setter
    @Getter
    private List<ProductOrder> productOrderList;

    public Order() {
    }

    public Order(LocalDateTime dateTime,
                 Long userId,
                 Double price) {
        this.dateTime = dateTime;
        this.userId = userId;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Odrer{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", userId=" + userId +
                ", price=" + price +
                '}';
    }
}
