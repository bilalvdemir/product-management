package com.products.code.domain.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class Book extends Product {

    private String author;

    @Builder
    public Book(Long id, String title, BigDecimal price, Integer stock,
                LocalDateTime createdAt, LocalDateTime updatedAt,
                String author) {
        super(id, title, price, stock, createdAt, updatedAt);
        this.author = author;
    }

    @Override
    public ProductType getProductType() {
        return ProductType.BOOK;
    }

    public void validateAuthor() {
        if (author == null || author.isBlank()) {
            throw new IllegalArgumentException("Author cannot be empty for books");
        }
    }
}
