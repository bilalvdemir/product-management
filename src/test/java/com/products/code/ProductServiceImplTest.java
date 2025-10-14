package com.products.code;

import com.products.code.application.service.ProductServiceImpl;
import com.products.code.domain.factory.ProductFactory;
import com.products.code.domain.factory.ProductFactoryRegistry;
import com.products.code.domain.model.Book;
import com.products.code.domain.model.Product;
import com.products.code.domain.model.ProductData;
import com.products.code.domain.model.ProductType;
import com.products.code.infrastructure.exception.ProductNotFoundException;
import com.products.code.infrastructure.persistence.entity.BookJpaEntity;
import com.products.code.infrastructure.persistence.entity.ProductJpaEntity;
import com.products.code.infrastructure.persistence.repository.jpa.JpaProductRepository;
import com.products.code.presentation.dto.ProductRequest;
import com.products.code.presentation.dto.ProductResponse;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private JpaProductRepository productRepository;

    @Mock
    private ProductFactoryRegistry factoryProvider;

    @Mock
    private ProductFactory productFactory;

    @Mock
    private Validator validator;

    @InjectMocks
    private ProductServiceImpl productService;

    private ProductRequest bookRequest;
    private ProductData productData;
    private BookJpaEntity bookEntity;
    private Product product;
    private ProductResponse bookResponse;

    @BeforeEach
    void setUp() {
        bookRequest = new ProductRequest(
                ProductType.BOOK,
                "My Book",
                new BigDecimal("49.99"),
                10,
                "Sami Gürel",
                null, null, null, null, null
        );

        productData = new ProductData(ProductType.BOOK,
                "My Book",
                new BigDecimal("49.99"),
                10,
                "Sami Gürel",
                null, null, null, null, null);

        product = Book.builder()
                .id(1L)
                .title("My Book")
                .price(new BigDecimal("49.99"))
                .author("Sami Gürel")
                .stock(10)
                .build();

        bookEntity = new BookJpaEntity();
        bookEntity.setId(1L);
        bookEntity.setTitle("My Book");
        bookEntity.setPrice(new BigDecimal("49.99"));
        bookEntity.setStock(10);
        bookEntity.setAuthor("Sami Gürel");

        bookResponse = ProductResponse.builder()
                .id(1L)
                .type(ProductType.BOOK)
                .title("My Book")
                .price(new BigDecimal("49.99"))
                .stock(10)
                .author("Sami Gürel")
                .build();
    }

    @Test
    @DisplayName("Should create product successfully")
    void createProduct_Success() {
        // Given
        when(factoryProvider.getFactory(ProductType.BOOK))
                .thenReturn(productFactory);
        when(productFactory.create(productData))
                .thenReturn(product);
        when(productRepository.save(any(ProductJpaEntity.class)))
                .thenReturn(bookEntity);

        when(validator.validate(any())).thenReturn(Collections.emptySet());
        // When
        Product result = productService.createProduct(productData);
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getTitle()).isEqualTo("My Book");
        assertThat(result.getStock()).isEqualTo(10);

        verify(productRepository).save(any(ProductJpaEntity.class));
        verify(factoryProvider).getFactory(ProductType.BOOK);
    }

    @Test
    @DisplayName("Should throw exception when product not found")
    void getProduct_NotFound() {
        // Given
        Long productId = 999L;
        when(productRepository.findById(productId))
                .thenReturn(Optional.empty());

        // When & Then
        assertThatThrownBy(() -> productService.getProduct(productId))
                .isInstanceOf(ProductNotFoundException.class)
                .hasMessageContaining("Product not found: 999");
    }
}