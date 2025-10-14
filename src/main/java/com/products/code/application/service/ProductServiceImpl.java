package com.products.code.application.service;

import com.products.code.application.ports.ProductService;
import com.products.code.domain.exception.ProductNotFoundException;
import com.products.code.domain.factory.ProductFactoryRegistry;
import com.products.code.domain.model.Product;
import com.products.code.domain.model.ProductData;
import com.products.code.domain.repository.ProductRepository;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductFactoryRegistry factoryProvider;
    private final ProductRepository productRepository;

    @Override
    @Timed(value = "product.create", description = "Time taken to create product")
    public Product createProduct(ProductData request) {

        log.info("Creating product: type:{}, title:{}", request.type(), request.title());
        request.validate();
        Product productEntity = factoryProvider.getFactory(request.type())
                .create(request);
        return productRepository.save(productEntity);
    }

    @Override
    @Cacheable(value = "products", key = "#id", unless = "#result == null")
    @Transactional(readOnly = true)
    public Product getProduct(Long id) {

        log.debug("Fetching product. id:{}", id);
        return productRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Product not found: id:{}", id);
                    return new ProductNotFoundException(id);
                });
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Product> getAllProducts(Pageable pageable) {

        log.debug("Fetching all products: page:{}, size:{}",
                pageable.getPageNumber(), pageable.getPageSize());
        return productRepository.findAll(pageable);
    }

    @Override
    @CacheEvict(value = "products", key = "#id")
    public void deleteProduct(Long id) {

        log.info("Deleting product: id:{}", id);
        if (!productRepository.existsById(id)) {
            log.error("Product not found for deletion: id:{}", id);
            throw new ProductNotFoundException(id);
        }
        productRepository.deleteById(id);
        log.info("Product deleted successfully: id:{}", id);
    }

    @Override
    @CachePut(value = "products", key = "#id")
    public Product updateProduct(Long id, ProductData product) {

        log.info("Updating product: id:{}", id);
        product.validate();
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Product not found for update: id:{}", id);
                    return new ProductNotFoundException(id);
                });
        Product productEntity = factoryProvider.getFactory(product.type()).update(existingProduct, product);
        Product response = productRepository.save(productEntity);
        log.info("Product updated successfully: id:{}", id);
        return response;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Product> searchProducts(String keyword, Pageable pageable) {

        log.debug("Searching products: keyword:{}, page:{}, size:{}",
                keyword, pageable.getPageNumber(), pageable.getPageSize());

        if (keyword == null || keyword.isBlank()) {
            return getAllProducts(pageable);
        }
        return productRepository.searchByTitle(keyword, pageable);
    }
}
