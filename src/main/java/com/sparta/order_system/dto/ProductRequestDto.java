package com.sparta.order_system.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductRequestDto {
    private String name;
    private Long price;
    private Long stock;
}
