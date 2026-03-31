package com.sparta.order_system.service;

import com.sparta.order_system.dto.ProductRequestDto;
import com.sparta.order_system.dto.ProductResponseDto;
import com.sparta.order_system.entity.Product;
import com.sparta.order_system.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    // 상품 등록
    public void createProduct(ProductRequestDto requestDto) {
        Product product = Product.builder()
                .name(requestDto.getName())
                .price(requestDto.getPrice())
                .stock(requestDto.getStock())
                .build();

        productRepository.save(product);
    }

    // 전체 상품 목록 조회
    public List<ProductResponseDto> getAllProducts() {
        return productRepository.findAllByIsDeletedFalse().stream()
                .map(ProductResponseDto::new)
                .toList();
    }

    // 특정 상품 조회
    public ProductResponseDto getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 존재하지 않습니다. ID: " + id));

        return new ProductResponseDto(product);
    }

    // 상품 수정
    @Transactional
    public ProductResponseDto updateProduct(Long id, ProductRequestDto requestDto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 존재하지 않습니다. ID: " + id));
        product.update(requestDto.getName(), requestDto.getPrice(), requestDto.getStock());

        return new ProductResponseDto(product);
    }

    // 상품 삭제
    @Transactional
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 존재하지 않습니다. ID: " + id));

//        productRepository.delete(product);
        product.softDelete();
    }
}
