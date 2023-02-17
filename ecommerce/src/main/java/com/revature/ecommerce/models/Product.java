package com.revature.ecommerce.models;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "products")
@Getter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    String name;
    String description;
    int price;

    public void setId(Long id) {
        this.id = id;
    }
}
