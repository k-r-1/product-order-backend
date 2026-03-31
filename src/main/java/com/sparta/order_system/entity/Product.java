package com.sparta.order_system.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Long price;
    private Long stock;

    private boolean isDeleted = false;

    @Builder
    public Product(String name, Long price, Long stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public void update(String name, Long price, Long stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public void softDelete() {
        this.isDeleted = true;
    }
}
