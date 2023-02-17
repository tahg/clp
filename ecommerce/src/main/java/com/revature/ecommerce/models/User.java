package com.revature.ecommerce.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "users")
@Getter
public class User {
    @Id
    @Column(name = "name", nullable = false, unique = true)
    String name;
    String password;
}
