package com.products.code.presentation.controller;

import com.products.code.application.ports.ProductService;
import com.products.code.domain.model.Product;
import com.products.code.domain.model.ProductData;
import com.products.code.presentation.dto.ApiResponse;
import com.products.code.presentation.dto.ProductRequest;
import com.products.code.presentation.dto.ProductResponse;
import com.products.code.presentation.mapper.ProductDtoMapper;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Product Management", description = "Marketplace API - Manage all product types")
public class ProductController {

    private final ProductService productService;
    private final ProductDtoMapper dtoMapper;

    @PostMapping
    @RateLimiter(name = "productService")
    @Operation(summary = "Create a new product",
            description = "Create a product any type (Book, Course, Magazine)")
    public ResponseEntity<ApiResponse<ProductResponse>> createProduct(@RequestBody @Valid ProductRequest request) {

        log.info("Creating product: type:{}, title:{}", request.type(), request.title());
        ProductData data = dtoMapper.toProductData(request);
        Product created = productService.createProduct(data);
        ProductResponse response = dtoMapper.toDto(created);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success("Product created successfully", response));
    }

    @GetMapping("/{id}")
    @RateLimiter(name = "productService")
    @Operation(summary = "Get product by ID",
            description = "Retrieve a product of any type by its ID")
    public ResponseEntity<ApiResponse<ProductResponse>> getProduct(@PathVariable Long id) {

        log.debug("Fetching product: id:{}", id);
        Product product = productService.getProduct(id);
        ProductResponse response = dtoMapper.toDto(product);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @GetMapping
    @RateLimiter(name = "productService")
    @Operation(summary = "Get all products with pagination",
            description = "Retrieve all products of all types with pagination")
    public ResponseEntity<ApiResponse<Page<ProductResponse>>> getAllProducts(
            @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC)
            Pageable pageable) {

        log.debug("Fetching all products: page:{}, size:{}",
                pageable.getPageNumber(), pageable.getPageSize());

        Page<Product> products = productService.getAllProducts(pageable);
        Page<ProductResponse> responses = products.map(dtoMapper::toDto);
        return ResponseEntity.ok(ApiResponse.success(responses));
    }

    @PutMapping("/{id}")
    @RateLimiter(name = "productService")
    @Operation(summary = "Update a product",
            description = "Update a product of any type")
    public ResponseEntity<ApiResponse<ProductResponse>> updateProduct(
            @PathVariable Long id,
            @RequestBody @Valid ProductRequest request) {

        log.info("Updating product: id:{}, type:{}", id, request.type());
        ProductData domain = dtoMapper.toProductData(request);
        Product updated = productService.updateProduct(id, domain);
        ProductResponse response = dtoMapper.toDto(updated);
        return ResponseEntity.ok(
                ApiResponse.success("Product updated successfully", response));
    }

    @DeleteMapping("/{id}")
    @RateLimiter(name = "productService")
    @Operation(summary = "Delete a product",
            description = "Delete a product of any type")
    public ResponseEntity<ApiResponse<Void>> deleteProduct(@PathVariable Long id) {

        log.info("Deleting product: id:{}", id);
        productService.deleteProduct(id);
        return ResponseEntity.ok(
                ApiResponse.success("Product deleted successfully", null));
    }

    @GetMapping("/search")
    @RateLimiter(name = "productService")
    @Operation(summary = "Search products by title",
            description = "Search products of all types by title keyword")
    public ResponseEntity<ApiResponse<Page<ProductResponse>>> searchProducts(
            @RequestParam(required = false) String keyword,
            @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC)
            Pageable pageable) {

        log.debug("Searching products: keyword:{}, page:{}, size:{}",
                keyword, pageable.getPageNumber(), pageable.getPageSize());
        Page<Product> products = productService.searchProducts(keyword, pageable);
        Page<ProductResponse> responses = products.map(dtoMapper::toDto);
        return ResponseEntity.ok(ApiResponse.success(responses));
    }
}
