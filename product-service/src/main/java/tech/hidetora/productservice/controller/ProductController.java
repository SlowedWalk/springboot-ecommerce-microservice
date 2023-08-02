package tech.hidetora.productservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tech.hidetora.productservice.dto.ApiResponse;
import tech.hidetora.productservice.dto.ProductDto;
import tech.hidetora.productservice.model.Product;
import tech.hidetora.productservice.service.ProductService;

import java.time.Instant;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse getAllProducts() {
        return ApiResponse.builder()
                .timestamp(Instant.now().toString())
                .message("Products fetched successfully")
                .statusCode(HttpStatus.OK.value())
                .success(Boolean.TRUE)
                .data(service
                        .getAllProduct()
                        .stream()
                        .map(ProductDto::fromProduct)
                        .toList())
                .build();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse getProductById(@PathVariable String id) {
        return ApiResponse.builder()
                .httpStatus(HttpStatus.OK)
                .success(Boolean.TRUE)
                .timestamp(Instant.now().toString())
                .message("Product fetched successfully")
                .statusCode(HttpStatus.OK.value())
                .data(ProductDto.fromProduct(service.getProduct(UUID.fromString(id))))
                .build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse createProduct(@RequestBody ProductDto product) {
        Product newProduct = service.createProduct(product);

        return ApiResponse.builder()
                .timestamp(Instant.now().toString())
                .message("Product created successfully")
                .statusCode(HttpStatus.CREATED.value())
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.CREATED)
                .data(ProductDto.fromProduct(newProduct))
                .build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ApiResponse deleteProduct(@PathVariable String id) {
        service.deleteProduct(UUID.fromString(id));

        return ApiResponse.builder()
                .timestamp(Instant.now().toString())
                .message("Product deleted successfully")
                .statusCode(HttpStatus.NO_CONTENT.value())
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.NO_CONTENT)
                .build();
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse updateProduct(@PathVariable String id, @RequestBody ProductDto product) {
        Product updatedProduct = service.updateProduct(UUID.fromString(id), product);

        return ApiResponse.builder()
                .timestamp(Instant.now().toString())
                .message("Product updated successfully")
                .statusCode(HttpStatus.OK.value())
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .data(ProductDto.fromProduct(updatedProduct))
                .build();
    }
}
