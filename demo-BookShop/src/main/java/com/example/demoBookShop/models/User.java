package com.example.demoBookShop.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_user",
            updatable = false)
    @Setter
    @Getter
    private Long id;

    @Column(name="first_name",
            nullable = false,
            columnDefinition = "VARCHAR(100)")
    @Setter
    @Getter
    private String firstName;

    @Column(name="last_name",
            nullable = false,
            columnDefinition = "VARCHAR(100)")
    @Setter
    @Getter
    private String lastName;

    @Column(name="email",
            nullable = false,
            columnDefinition = "VARCHAR(100)")
    @Setter
    @Getter
    private String email;

    @Column(name="role",
            nullable = false,
            columnDefinition = "VARCHAR(100)")
    @Setter
    @Getter
    private String role;

    @Column(name="address",
            nullable = false,
            columnDefinition = "VARCHAR(100)")
    @Setter
    @Getter
    private String address;

    @Column(name="phone",
            nullable = false,
            columnDefinition = "VARCHAR(10)")
    @Setter
    @Getter
    private String phone;

    @Column(name="cardNumber",
            nullable = false,
            columnDefinition = "VARCHAR(16)")
    @Setter
    @Getter
    private String cardNumber;

    @Column(name="password",
            nullable = false,
            columnDefinition = "VARCHAR(100)")
    @Setter
    @Getter
    private String password;

    @OneToMany(mappedBy = "userData")
    List<Order> listOfOrder;

    public User() {
    }

    public User(String firstName,
                String lastName,
                String email,
                String role,
                String address,
                String phone,
                String cardNumber,
                String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.address = address;
        this.phone = phone;
        this.cardNumber = cardNumber;
        this.password = password;
    }
}
