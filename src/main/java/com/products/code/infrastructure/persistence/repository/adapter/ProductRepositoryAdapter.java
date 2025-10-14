package com.products.code.infrastructure.persistence.repository.adapter;

import com.products.code.domain.model.Product;
import com.products.code.domain.repository.ProductRepository;
import com.products.code.infrastructure.persistence.entity.ProductJpaEntity;
import com.products.code.infrastructure.persistence.mapper.ProductEntityMapper;
import com.products.code.infrastructure.persistence.repository.jpa.JpaProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ProductRepositoryAdapter implements ProductRepository {

    private final JpaProductRepository jpaRepository;
    private final ProductEntityMapper mapper;

    @Override
    public Product save(Product product) {
        log.debug("Saving product: {}", product.getTitle());
        ProductJpaEntity entity = mapper.toEntity(product);
        ProductJpaEntity saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Product> findById(Long id) {
        log.debug("Finding product by id: {}", id);
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        log.debug("Finding all products with pagination");
        return jpaRepository.findAll(pageable)
                .map(mapper::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        log.debug("Deleting product by id: {}", id);
        jpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return jpaRepository.existsById(id);
    }

    @Override
    public Page<Product> searchByTitle(String keyword, Pageable pageable) {
        log.debug("Searching products by title: {}", keyword);
        return jpaRepository.searchByTitle(keyword, pageable)
                .map(mapper::toDomain);
    }
}
