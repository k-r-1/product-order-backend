package com.sparta.order_system.controller;

import com.sparta.order_system.dto.OrderRequestDto;
import com.sparta.order_system.dto.OrderResponseDto;
import com.sparta.order_system.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/orders")
    public OrderResponseDto createOrder(@RequestBody OrderRequestDto requestDto) {
        return orderService.createOrder(requestDto);
    }

    @GetMapping("/orders/{orderId}")
    public OrderResponseDto getOrder(@PathVariable("orderId") Long orderId) {
        return orderService.getOrder(orderId);
    }

    @GetMapping("/orders")
    public Page<OrderResponseDto> getOrderList(Pageable pageable) {
        return orderService.getOrderList(pageable);
    }
}
