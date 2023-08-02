package tech.hidetora.productservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.hidetora.productservice.dto.ProductDto;
import tech.hidetora.productservice.enumeration.ErrorCodes;
import tech.hidetora.productservice.exception.ProductAlreadyExistException;
import tech.hidetora.productservice.exception.ProductNotFoundException;
import tech.hidetora.productservice.model.Product;
import tech.hidetora.productservice.repository.ProductRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository repository;

    public Product createProduct(ProductDto productDto) {
        Optional<Product> product = repository.findByName(productDto.getName());
        if (product.isPresent()) {
            throw new ProductAlreadyExistException("Product already exist with name: " + productDto.getName(), ErrorCodes.PRODUCT_ALREADY_EXIST);
        }

        Product newProduct = ProductDto.toProduct(productDto);
        log.info("New product created : {}", newProduct.getName());
        return repository.save(newProduct);
    }

    public Product updateProduct(UUID id, ProductDto productDto) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id, ErrorCodes.PRODUCT_NOT_FOUND));
        if (productDto.getName() != null || productDto.getDescription() != null || productDto.getPrice() != null || productDto.getQuantity() != 0) {
            product.setName(productDto.getName());
            product.setDescription(productDto.getDescription());
            product.setPrice(productDto.getPrice());
            product.setQuantity(productDto.getQuantity());
        }
        log.info("Product updated: {}", product.getId());
        return repository.save(product);
    }

    public void deleteProduct(UUID id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id, ErrorCodes.PRODUCT_NOT_FOUND));
        repository.delete(product);
        log.info("Product deleted: {}", product.getId());
    }

    public List<Product> getAllProduct() {
        log.info("All products fetched");
        return repository.findAll();
    }

    public Product getProduct(UUID id) {
        log.info("Product fetched: {}", id);
        return repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id, ErrorCodes.PRODUCT_NOT_FOUND));
    }
}
