package com.example.demoBookShop.models;

import com.fasterxml.jackson.annotation.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name="Users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user",
            updatable = false)
    private Long id;

    @Column(name = "first_name",
            nullable = false,
            columnDefinition = "VARCHAR(100)")
    private String firstName;

    @Column(name = "last_name",
            nullable = false,
            columnDefinition = "VARCHAR(100)")
    private String lastName;

    @Column(name = "user_name",
            nullable = false,
            columnDefinition = "VARCHAR(100)")
    private String userName;

    @Column(name = "email",
            nullable = false,
            columnDefinition = "VARCHAR(100)")
    private String email;

//    @Column(name="role",
//            nullable = false,
//            columnDefinition = "VARCHAR(100)")
//    private String role;

    @Column(name = "address",
            nullable = false,
            columnDefinition = "VARCHAR(100)")
    private String address;

    @Column(name = "phone",
            nullable = false,
            columnDefinition = "VARCHAR(10)")
    private String phone;

    @Column(name = "cardNumber",
            nullable = false,
            columnDefinition = "VARCHAR(16)")
    private String cardNumber;

    @Column(name = "password",
            nullable = false,
            columnDefinition = "VARCHAR(100)")
    private String password;

    @OneToMany(mappedBy = "userDt",fetch= FetchType.EAGER)
    private Collection<UserRole> userRole;

    @JsonIgnore
    @OneToMany(mappedBy = "userData")
    List<Order> listOfOrder;

    public User() {
    }

    public User(String firstName,
                String lastName,
                String email,
                String address,
                String phone,
                String cardNumber,
                String password,
                String userName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userName = userName;
        this.address = address;
        this.phone = phone;
        this.cardNumber = cardNumber;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<UserRole> getUserRole() {
        return userRole;
    }

    public void setUserRole(Collection<UserRole> userRole) {
        this.userRole = userRole;
    }

    public List<Order> getListOfOrder() {
        return listOfOrder;
    }

    public void setListOfOrder(List<Order> listOfOrder) {
        this.listOfOrder = listOfOrder;
    }
}
