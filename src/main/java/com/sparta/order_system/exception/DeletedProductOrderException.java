package com.sparta.order_system.exception;

public class DeletedProductOrderException extends RuntimeException {
    public DeletedProductOrderException(String message) {
        super(message);
    }
}
