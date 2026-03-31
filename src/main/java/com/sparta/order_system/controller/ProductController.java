package com.sparta.order_system.controller;

import com.sparta.order_system.dto.ProductRequestDto;
import com.sparta.order_system.dto.ProductResponseDto;
import com.sparta.order_system.entity.Product;
import com.sparta.order_system.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/products")
    public String createProduct(@RequestBody ProductRequestDto requestDto) {
        productService.createProduct(requestDto);
        return "상품 등록 완료";
    }

    @GetMapping("/products")
    public List<ProductResponseDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public ProductResponseDto getProductById(@PathVariable("id") Long id) {
        return productService.getProductById(id);
    }

    @PutMapping("/products/{id}")
    public ProductResponseDto updateProduct(@PathVariable("id") Long id, @RequestBody ProductRequestDto requestDto) {
        return productService.updateProduct(id, requestDto);
    }

    @DeleteMapping("/products/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return "상품 삭제 완료";
    }

}
