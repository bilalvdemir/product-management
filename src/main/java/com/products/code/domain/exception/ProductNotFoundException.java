package com.products.code.domain.exception;

/**
 * Domain Exception - Product Not Found
 */
public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Long id) {
        super(String.format("Product not found with id: %s", id));
    }
}
