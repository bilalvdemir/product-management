package com.products.code.domain.model;

import com.products.code.domain.exception.InsufficientStockException;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public abstract class Product {

    private Long id;
    private String title;
    private BigDecimal price;
    private Integer stock;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Pure business logic - No JPA!
    public void decreaseStock(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        if (this.stock < quantity) {
            throw new InsufficientStockException(
                String.format("Insufficient stock for product %d. Available: %d, Requested: %d",
                    this.id, this.stock, quantity)
            );
        }
        this.stock -= quantity;
    }

    public void increaseStock(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        this.stock += quantity;
    }

    public boolean isAvailable(int quantity) {
        return this.stock >= quantity;
    }

    public void validatePrice() {
        if (this.price == null || this.price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price must be greater than zero");
        }
    }

    public void validateStock() {
        if (this.stock == null || this.stock < 0) {
            throw new IllegalArgumentException("Stock cannot be negative");
        }
    }

    public void validateTitle() {
        if (this.title == null || this.title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
    }

    public abstract ProductType getProductType();
}
