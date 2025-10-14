package com.products.code.domain.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class Magazine extends Product {

    private String publisher;
    private String publicationMonth;

    @Builder
    public Magazine(Long id, String title, BigDecimal price, Integer stock,
                    LocalDateTime createdAt, LocalDateTime updatedAt,
                    String publisher, String publicationMonth) {
        super(id, title, price, stock, createdAt, updatedAt);
        this.publisher = publisher;
        this.publicationMonth = publicationMonth;
    }

    @Override
    public ProductType getProductType() {
        return ProductType.MAGAZINE;
    }

    public void validatePublisher() {
        if (publisher == null || publisher.isBlank()) {
            throw new IllegalArgumentException("Publisher cannot be empty for magazines");
        }
    }

    public void validatePublicationMonth() {
        if (publicationMonth == null || publicationMonth.isBlank()) {
            throw new IllegalArgumentException("Publication month cannot be empty for magazines");
        }
    }
}
