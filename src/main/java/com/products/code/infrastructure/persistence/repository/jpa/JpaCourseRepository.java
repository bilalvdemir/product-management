package com.products.code.infrastructure.persistence.repository.jpa;

import com.products.code.infrastructure.persistence.entity.CourseJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCourseRepository extends JpaRepository<CourseJpaEntity, Long> {
}
