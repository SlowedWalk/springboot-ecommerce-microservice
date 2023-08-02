package tech.hidetora.productservice.dto;

import lombok.*;
import tech.hidetora.productservice.enumeration.CategoryName;
import tech.hidetora.productservice.enumeration.Status;
import tech.hidetora.productservice.model.Product;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
@RequiredArgsConstructor
public class ProductDto {
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
    private String imageUrl;
    private int quantity;
    private String category;
    private String status;

    public static Product toProduct(ProductDto productDto) {
        CategoryName category = CategoryName.valueOf(productDto.getCategory());
        Status status = Status.valueOf(productDto.getStatus());

        Product product = Product.builder()
                .name(productDto.getName())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .imageUrl(productDto.getImageUrl())
                .quantity(productDto.getQuantity())
                .category(category)
                .status(status)
                .build();
        if (productDto.getId() != null) {
            product.setId(UUID.fromString(productDto.getId()));
        }
        return product;
    }

    public static ProductDto fromProduct(Product product) {
        String category = product.getCategory().name();
        String status = product.getStatus().name();

        return ProductDto.builder()
                .id(product.getId().toString())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .imageUrl(product.getImageUrl())
                .quantity(product.getQuantity())
                .category(category)
                .status(status)
                .build();
    }
}
