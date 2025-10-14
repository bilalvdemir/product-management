package com.products.code.domain.repository;

import com.products.code.domain.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Domain Repository Interface
 * Defines contract for product persistence
 * Infrastructure layer will implement this
 */
public interface ProductRepository {
    Product save(Product product);
    Optional<Product> findById(Long id);
    Page<Product> findAll(Pageable pageable);
    void deleteById(Long id);
    boolean existsById(Long id);
    Page<Product> searchByTitle(String keyword, Pageable pageable);
}
