package com.products.code.domain.factory;

import com.products.code.domain.model.ProductType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Registry for managing product factories
 * Acts as a factory provider based on product type
 */
@Component
public class ProductFactoryRegistry {

    private final Map<ProductType, ProductFactory> factories;

    public ProductFactoryRegistry(List<ProductFactory> factoryList) {
        this.factories = factoryList.stream()
                .collect(Collectors.toMap(
                        ProductFactory::getSupportedType,
                        Function.identity()
                ));
    }

    public ProductFactory getFactory(ProductType productType) {
        ProductFactory factory = factories.get(productType);
        if (factory == null) {
            throw new IllegalArgumentException("Unsupported product type: " + productType);
        }
        return factory;
    }
}
