package com.products.code.domain.factory;

import com.products.code.domain.model.Book;
import com.products.code.domain.model.Product;
import com.products.code.domain.model.ProductData;
import com.products.code.domain.model.ProductType;
import org.springframework.stereotype.Component;

/**
 * Factory for creating Book domain objects
 */
@Component
public class BookFactory implements ProductFactory {

    @Override
    public Book create(ProductData data) {
        return Book.builder()
                .title(data.title())
                .price(data.price())
                .stock(data.stock())
                .author(data.author())
                .build();
    }

    @Override
    public Book update(Product existing, ProductData data) {
        Book book = (Book) existing;
        book.setTitle(data.title());
        book.setPrice(data.price());
        book.setStock(data.stock());
        book.setAuthor(data.author());
        return book;
    }

    @Override
    public ProductType getSupportedType() {
        return ProductType.BOOK;
    }
}
