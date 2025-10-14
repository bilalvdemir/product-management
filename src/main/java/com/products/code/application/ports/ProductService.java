package com.products.code.application.ports;

import com.products.code.domain.model.Product;
import com.products.code.domain.model.ProductData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Application Service Interface
 * Uses domain models only - NO DTOs
 */
public interface ProductService {
    Product createProduct(ProductData product);
    Product getProduct(Long id);
    Page<Product> getAllProducts(Pageable pageable);
    Product updateProduct(Long id, ProductData product);
    void deleteProduct(Long id);
    Page<Product> searchProducts(String keyword, Pageable pageable);
}
