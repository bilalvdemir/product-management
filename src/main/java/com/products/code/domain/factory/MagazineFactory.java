package com.products.code.domain.factory;

import com.products.code.domain.model.Magazine;
import com.products.code.domain.model.Product;
import com.products.code.domain.model.ProductData;
import com.products.code.domain.model.ProductType;
import org.springframework.stereotype.Component;

/**
 * Factory for creating Magazine domain objects
 */
@Component
public class MagazineFactory implements ProductFactory {

    @Override
    public Magazine create(ProductData data) {
        return Magazine.builder()
                .title(data.title())
                .price(data.price())
                .stock(data.stock())
                .publisher(data.publisher())
                .publicationMonth(data.publicationMonth())
                .build();
    }

    @Override
    public Magazine update(Product existing, ProductData data) {
        Magazine magazine = (Magazine) existing;
        magazine.setTitle(data.title());
        magazine.setPrice(data.price());
        magazine.setStock(data.stock());
        magazine.setPublisher(data.publisher());
        magazine.setPublicationMonth(data.publicationMonth());
        return magazine;
    }

    @Override
    public ProductType getSupportedType() {
        return ProductType.MAGAZINE;
    }
}
