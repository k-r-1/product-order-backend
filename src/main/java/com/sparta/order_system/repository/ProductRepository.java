package com.sparta.order_system.repository;

import com.sparta.order_system.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByIsDeletedFalse();
}
