package com.sparta.order_system.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.sparta.order_system.dto.ErrorResponseDto;
import com.sparta.order_system.entity.Product;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 재고 부족
    @ExceptionHandler(NotEnoughStockException.class)
    public ResponseEntity<ErrorResponseDto> handleNotEnoughStockException(NotEnoughStockException ex) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(ex.getMessage(), 409);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponseDto);
    }

    // 상품 없음
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleProductNotFoundException(ProductNotFoundException ex) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(ex.getMessage(), 404);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponseDto);
    }

    // 주문 없음
    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleOrderNotFoundException(OrderNotFoundException ex) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(ex.getMessage(), 404);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponseDto);
    }

    // 삭제된 상품 주문
    @ExceptionHandler(DeletedProductOrderException.class)
    public ResponseEntity<ErrorResponseDto> handleDeletedProductOrderException(DeletedProductOrderException ex) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(ex.getMessage(), 400);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseDto);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDto> handleIllegalArgumentException(IllegalArgumentException ex) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(ex.getMessage(), 400);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseDto);
    }

}
