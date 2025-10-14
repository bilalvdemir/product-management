package com.products.code.domain.factory;

import com.products.code.domain.model.Course;
import com.products.code.domain.model.Product;
import com.products.code.domain.model.ProductData;
import com.products.code.domain.model.ProductType;
import org.springframework.stereotype.Component;

/**
 * Factory for creating Course domain objects
 */
@Component
public class CourseFactory implements ProductFactory {

    @Override
    public Course create(ProductData data) {
        return Course.builder()
                .title(data.title())
                .price(data.price())
                .stock(data.stock())
                .instructor(data.instructor())
                .durationHours(data.durationHours())
                .level(data.level())
                .build();
    }

    @Override
    public Course update(Product existing, ProductData data) {
        Course course = (Course) existing;
        course.setTitle(data.title());
        course.setPrice(data.price());
        course.setStock(data.stock());
        course.setInstructor(data.instructor());
        course.setDurationHours(data.durationHours());
        course.setLevel(data.level());
        return course;
    }

    @Override
    public ProductType getSupportedType() {
        return ProductType.COURSE;
    }
}
