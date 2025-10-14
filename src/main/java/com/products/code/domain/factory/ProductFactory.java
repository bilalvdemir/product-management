package com.products.code.domain.factory;

import com.products.code.domain.model.Product;
import com.products.code.domain.model.ProductData;
import com.products.code.domain.model.ProductType;

/**
 * Factory interface for creating domain products
 * Uses ProductData value object instead of DTOs
 */
public interface ProductFactory {
    
    /**
     * Create a new product from ProductData
     */
    Product create(ProductData data);
    
    /**
     * Update existing product with new ProductData
     */
    Product update(Product existing, ProductData data);
    
    /**
     * Get supported product type
     */
    ProductType getSupportedType();
}
