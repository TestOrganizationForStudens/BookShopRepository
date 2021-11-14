package com.example.demoBookShop.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="products_order")
public class ProductOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_product_order",
            updatable = false)
    @Setter
    @Getter
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id",
            referencedColumnName = "id_order",
            nullable = false)
    @Setter
    @Getter
    Order order;

    @ManyToOne
    @JoinColumn(name = "product_id",
            referencedColumnName = "id_product",
            nullable = false)
    @Setter
    @Getter
    Product product;

    @Column(name="amount",
            nullable = false,
            columnDefinition = "INT")
    @Setter
    @Getter
    private Integer amount;

    public ProductOrder(Order order, Product product, Integer amount) {
        this.order = order;
        this.product = product;
        this.amount = amount;
    }

    public ProductOrder(Product product, Integer amount) {
        this.product = product;
        this.amount = amount;
    }

    public ProductOrder() {

    }
}
