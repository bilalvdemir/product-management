package com.products.code.domain.model;

import java.math.BigDecimal;

/**
 * Value Object for product creation/update
 * Contains all properties for all product types
 * Domain layer - Framework independent
 */
public record ProductData(
        ProductType type,
        String title,
        BigDecimal price,
        Integer stock,

        // Book specific
        String author,

        // Course specific
        String instructor,
        Integer durationHours,
        String level,

        // Magazine specific
        String publisher,
        String publicationMonth
) {
    public void validate() {
        if (type == null) {
            throw new IllegalArgumentException("Product type is required");
        }
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title is required");
        }
        if (price == null || price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price must be positive");
        }
        if (stock == null || stock < 0) {
            throw new IllegalArgumentException("Stock cannot be negative");
        }

        // Type-specific validation
        switch (type) {
            case BOOK -> {
                if (author == null || author.isBlank()) {
                    throw new IllegalArgumentException("Author is required for books");
                }
            }
            case COURSE -> {
                if (instructor == null || instructor.isBlank()) {
                    throw new IllegalArgumentException("Instructor is required for courses");
                }
                if (durationHours == null || durationHours <= 0) {
                    throw new IllegalArgumentException("Duration must be positive for courses");
                }
            }
            case MAGAZINE -> {
                if (publisher == null || publisher.isBlank()) {
                    throw new IllegalArgumentException("Publisher is required for magazines");
                }
            }
        }
    }
}
