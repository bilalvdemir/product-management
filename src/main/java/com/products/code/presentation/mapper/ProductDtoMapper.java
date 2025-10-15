package com.products.code.presentation.mapper;

import com.products.code.domain.model.*;
import com.products.code.presentation.dto.CreateProductCommand;
import com.products.code.presentation.dto.GetProductQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Converts between DTOs and Domain Models
 */
@Component
@RequiredArgsConstructor
public class ProductDtoMapper {

    /**
     * Convert ProductRequest DTO to ProductData
     */
    public ProductData toProductData(CreateProductCommand dto) {
        return new ProductData(
                dto.type(),
                dto.title(),
                dto.price(),
                dto.stock(),
                dto.author(),
                dto.instructor(),
                dto.durationHours(),
                dto.level(),
                dto.publisher(),
                dto.publicationMonth()
        );
    }

    /**
     * Convert Product to ProductResponse
     */
    public GetProductQuery toDto(Product domain) {
        if (domain == null) {
            return null;
        }

        GetProductQuery.GetProductQueryBuilder builder = GetProductQuery.builder()
                .id(domain.getId())
                .type(domain.getProductType())
                .title(domain.getTitle())
                .price(domain.getPrice())
                .stock(domain.getStock())
                .createdAt(domain.getCreatedAt())
                .updatedAt(domain.getUpdatedAt());

        return switch (domain.getProductType()) {
            case BOOK -> {
                Book book = (Book) domain;
                yield builder
                        .author(book.getAuthor())
                        .build();
            }
            case COURSE -> {
                Course course = (Course) domain;
                yield builder
                        .instructor(course.getInstructor())
                        .durationHours(course.getDurationHours())
                        .level(course.getLevel())
                        .build();
            }
            case MAGAZINE -> {
                Magazine magazine = (Magazine) domain;
                yield builder
                        .publisher(magazine.getPublisher())
                        .publicationMonth(magazine.getPublicationMonth())
                        .build();
            }
        };
    }
}
