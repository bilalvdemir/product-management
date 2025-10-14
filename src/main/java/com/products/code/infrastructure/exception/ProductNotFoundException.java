package com.products.code.infrastructure.exception;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(Long id) {
        super(String.format("Product not found: %s", id));
    }
}