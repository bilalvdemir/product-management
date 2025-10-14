package com.products.code.infrastructure.persistence.repository.jpa;

import com.products.code.infrastructure.persistence.entity.ProductJpaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JpaProductRepository extends JpaRepository<ProductJpaEntity, Long> {

    @Query("SELECT p FROM ProductJpaEntity p WHERE p.title LIKE %:keyword%")
    Page<ProductJpaEntity> searchByTitle(@Param("keyword") String keyword, Pageable pageable);
}
