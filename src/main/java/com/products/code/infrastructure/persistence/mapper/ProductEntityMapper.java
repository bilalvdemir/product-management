package com.products.code.infrastructure.persistence.mapper;

import com.products.code.domain.model.*;
import com.products.code.infrastructure.persistence.entity.*;
import org.springframework.stereotype.Component;

@Component
public class ProductEntityMapper {

    public Product toDomain(ProductJpaEntity entity) {
        if (entity == null) {
            return null;
        }

        return switch (entity.getDiscriminatorValue()) {
            case "BOOK" -> toBook((BookJpaEntity) entity);
            case "COURSE" -> toCourse((CourseJpaEntity) entity);
            case "MAGAZINE" -> toMagazine((MagazineJpaEntity) entity);
            default -> throw new IllegalArgumentException("Unknown product type: " + entity.getDiscriminatorValue());
        };
    }

    public ProductJpaEntity toEntity(Product domain) {
        if (domain == null) {
            return null;
        }

        return switch (domain.getProductType()) {
            case BOOK -> toBookEntity((Book) domain);
            case COURSE -> toCourseEntity((Course) domain);
            case MAGAZINE -> toMagazineEntity((Magazine) domain);
        };
    }

    private Book toBook(BookJpaEntity entity) {
        return Book.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .price(entity.getPrice())
                .stock(entity.getStock())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .author(entity.getAuthor())
                .build();
    }

    private BookJpaEntity toBookEntity(Book domain) {
        BookJpaEntity entity = new BookJpaEntity();
        entity.setId(domain.getId());
        entity.setTitle(domain.getTitle());
        entity.setPrice(domain.getPrice());
        entity.setStock(domain.getStock());
        entity.setCreatedAt(domain.getCreatedAt());
        entity.setUpdatedAt(domain.getUpdatedAt());
        entity.setAuthor(domain.getAuthor());
        return entity;
    }

    private Course toCourse(CourseJpaEntity entity) {
        return Course.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .price(entity.getPrice())
                .stock(entity.getStock())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .instructor(entity.getInstructor())
                .durationHours(entity.getDurationHours())
                .level(entity.getLevel())
                .build();
    }

    private CourseJpaEntity toCourseEntity(Course domain) {
        CourseJpaEntity entity = new CourseJpaEntity();
        entity.setId(domain.getId());
        entity.setTitle(domain.getTitle());
        entity.setPrice(domain.getPrice());
        entity.setStock(domain.getStock());
        entity.setCreatedAt(domain.getCreatedAt());
        entity.setUpdatedAt(domain.getUpdatedAt());
        entity.setInstructor(domain.getInstructor());
        entity.setDurationHours(domain.getDurationHours());
        entity.setLevel(domain.getLevel());
        return entity;
    }

    private Magazine toMagazine(MagazineJpaEntity entity) {
        return Magazine.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .price(entity.getPrice())
                .stock(entity.getStock())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .publisher(entity.getPublisher())
                .publicationMonth(entity.getPublicationMonth())
                .build();
    }

    private MagazineJpaEntity toMagazineEntity(Magazine domain) {
        MagazineJpaEntity entity = new MagazineJpaEntity();
        entity.setId(domain.getId());
        entity.setTitle(domain.getTitle());
        entity.setPrice(domain.getPrice());
        entity.setStock(domain.getStock());
        entity.setCreatedAt(domain.getCreatedAt());
        entity.setUpdatedAt(domain.getUpdatedAt());
        entity.setPublisher(domain.getPublisher());
        entity.setPublicationMonth(domain.getPublicationMonth());
        return entity;
    }
}
