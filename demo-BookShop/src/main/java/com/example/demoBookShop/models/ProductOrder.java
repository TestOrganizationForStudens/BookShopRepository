package com.example.demoBookShop.models;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="products_order")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class ProductOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_product_order",
            updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id",
            referencedColumnName = "id_order",
            nullable = false)
    Order order;

    @ManyToOne
    @JoinColumn(name = "product_id",
            referencedColumnName = "id_product",
            nullable = false)
    Product product;

    @Column(name="amount",
            nullable = false,
            columnDefinition = "INT")
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
