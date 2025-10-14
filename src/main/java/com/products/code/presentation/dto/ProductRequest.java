package com.products.code.presentation.dto;

import com.products.code.domain.model.ProductType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductRequest(
        @NotNull(message = "Type cannot be null")
        ProductType type,

        @NotNull(message = "Title cannot be null")
        @NotBlank(message = "Title cannot be null")
        String title,

        @NotNull(message = "Price cannot be null")
        @DecimalMin(value = "0.01", message = "Price must be positive")
        BigDecimal price,

        @NotNull(message = "Stok cannot be null")
        @Min(value = 0, message = "Stock cannot be negative")
        Integer stock,

        // Book specific fields
        String author,

        // Magazine specific fields
        String publisher,
        String publicationMonth,

        // Course specific fields
        String instructor,
        Integer durationHours,
        String level
) {
}
