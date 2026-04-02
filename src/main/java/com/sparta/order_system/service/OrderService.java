package com.sparta.order_system.service;

import com.sparta.order_system.dto.OrderRequestDto;
import com.sparta.order_system.dto.OrderResponseDto;
import com.sparta.order_system.entity.Order;
import com.sparta.order_system.entity.Product;
import com.sparta.order_system.repository.OrderRepository;
import com.sparta.order_system.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Transactional
    public OrderResponseDto createOrder(OrderRequestDto requestDto) {
        Long productId = requestDto.getProductId();
        Product product = productRepository.findByIdWithPessimisticLock(productId)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 존재하지 않습니다. ID: " + productId));

        if (product.isDeleted()) {
            throw new IllegalArgumentException("현재 판매 중지된 상품입니다.");
        }

        // 재고 차감
        product.decreaseStock(requestDto.getQuantity());

        Order order = Order.builder()
                .product(product)
                .quantity(requestDto.getQuantity())
                .build();

        Order saveOrder = orderRepository.save(order);

        return new OrderResponseDto(saveOrder);
    }

    @Transactional(readOnly = true)
    public OrderResponseDto getOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("해당 주문이 존재하지 않습니다. ID: " + orderId));

        return new OrderResponseDto(order);
    }

    @Transactional(readOnly = true)
    public Page<OrderResponseDto> getOrderList(Pageable pageable) {
        Page<Order> orderPage = orderRepository.findAllWithProduct(pageable);
        return orderPage.map(OrderResponseDto::new);
    }
}
