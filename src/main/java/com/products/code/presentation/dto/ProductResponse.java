package com.products.code.presentation.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.products.code.domain.model.ProductType;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ProductResponse(

        Long id,

        ProductType type,

        String title,

        BigDecimal price,

        Integer stock,

        LocalDateTime createdAt,

        LocalDateTime updatedAt,

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
