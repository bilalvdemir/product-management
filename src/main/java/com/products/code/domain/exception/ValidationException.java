package com.products.code.domain.exception;

/**
 * Domain Exception - Validation Error
 * Business logic validation errors
 */
public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
