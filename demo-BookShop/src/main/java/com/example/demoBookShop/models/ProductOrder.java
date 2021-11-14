package com.example.demoBookShop.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="products_order")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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
    @JsonIgnore
   // @JsonProperty(access = Access.WRITE_ONLY)
    @Setter
    @Getter
    Order order;

    @ManyToOne
    @JoinColumn(name = "product_id",
            referencedColumnName = "id_product",
            nullable = false)
   // @JsonIgnore
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

//    public Order getOrder() {
//        return order;
//    }
//
//    public void setOrder(Order order) {
//        this.order = order;
//    }
//
//    public Product getProduct() {
//        return product;
//    }
//
//    public void setProduct(Product product) {
//        this.product = product;
//    }
}
