package com.example.demoBookShop.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
@Table(name="user_role")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_user_role",
            updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id",
            referencedColumnName = "id_user",
            nullable = false)
    User userDt;

    @ManyToOne
    @JoinColumn(name = "role_id",
            referencedColumnName = "id_role",
            nullable = false)
    Role roleData;

    public UserRole(User user, Role role) {
        this.userDt = user;
        this.roleData = role;
    }
    public UserRole() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUserDt() {
        return userDt;
    }

    public void setUserDt(User userDt) {
        this.userDt = userDt;
    }

    public Role getRoleData() {
        return roleData;
    }

    public void setRoleData(Role roleData) {
        this.roleData = roleData;
    }
}
