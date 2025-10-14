package com.products.code.infrastructure.exception;

/**
 * Business logic validation hatalarını temsil eder.
 * Bean Validation API hatalarından ayrıştırmak için kullanılır.
 */
public class ValidationException extends RuntimeException {

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
