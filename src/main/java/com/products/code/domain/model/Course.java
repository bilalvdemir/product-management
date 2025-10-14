package com.products.code.domain.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class Course extends Product {

    private String instructor;
    private Integer durationHours;
    private String level;

    @Builder
    public Course(Long id, String title, BigDecimal price, Integer stock,
                  LocalDateTime createdAt, LocalDateTime updatedAt,
                  String instructor, Integer durationHours, String level) {
        super(id, title, price, stock, createdAt, updatedAt);
        this.instructor = instructor;
        this.durationHours = durationHours;
        this.level = level;
    }

    @Override
    public ProductType getProductType() {
        return ProductType.COURSE;
    }

    public void validateDuration() {
        if (durationHours == null || durationHours <= 0) {
            throw new IllegalArgumentException("Course duration must be positive");
        }
    }

    public void validateInstructor() {
        if (instructor == null || instructor.isBlank()) {
            throw new IllegalArgumentException("Instructor cannot be empty for courses");
        }
    }
}
