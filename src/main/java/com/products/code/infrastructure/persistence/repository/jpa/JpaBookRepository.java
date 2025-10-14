package com.products.code.infrastructure.persistence.repository.jpa;

import com.products.code.infrastructure.persistence.entity.BookJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaBookRepository extends JpaRepository<BookJpaEntity, Long> {
}
