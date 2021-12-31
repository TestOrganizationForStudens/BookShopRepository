package com.example.demoBookShop.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name="roles")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_role",
            updatable = false)
    private Long id;
    private String role;

    @OneToMany(mappedBy = "roleData")
    @JsonIgnore
    private Collection<UserRole> usersRole;

    public Role(String role) {
        this.role = role;
    }

    public Role() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Collection<UserRole> getUsersRole() {
        return usersRole;
    }

    public void setUsersRole(Collection<UserRole> usersRole) {
        this.usersRole = usersRole;
    }
}
