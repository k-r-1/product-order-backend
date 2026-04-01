package com.sparta.order_system.dto;

import com.sparta.order_system.entity.Order;
import lombok.Getter;

@Getter
public class OrderResponseDto {
    private Long orderId;
    private Long productId;
    private String productName;

    public OrderResponseDto(Order order) {
        this.orderId = order.getId();
        this.productId = order.getProduct().getId();
        this.productName = order.getProduct().getName();
    }
}
