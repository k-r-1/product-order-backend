package com.sparta.order_system.dto;

import com.sparta.order_system.entity.Product;
import lombok.Getter;

@Getter
public class ProductResponseDto {
    private Long id;
    private String name;
    private Long price;
    private Long stock;

    public ProductResponseDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.stock = product.getStock();
    }
}
