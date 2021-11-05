package model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name="products_order")
public class ProductOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_product_order",
            updatable = false)
    @Setter
    @Getter
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id",
            nullable = false)
    Order order;

    @ManyToOne
    @JoinColumn(name = "product_id",
            nullable = false)
    Product product;

    @Column(name="amount",
            nullable = false,
            columnDefinition = "INT")
    @Setter
    @Getter
    private Integer amount;
}
