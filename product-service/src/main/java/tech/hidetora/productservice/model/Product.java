package tech.hidetora.productservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import tech.hidetora.productservice.enumeration.CategoryName;
import tech.hidetora.productservice.enumeration.Status;

import java.math.BigDecimal;
import java.util.UUID;

import static jakarta.persistence.GenerationType.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Product {
    @Id
    @GeneratedValue(strategy = UUID)
    private UUID id;
    private String name;
    private String description;
    private BigDecimal price;
    @Column(name = "image_url")
    private String imageUrl;
    private int quantity;
    @Enumerated(EnumType.STRING)
    private CategoryName category;
    @Enumerated(EnumType.STRING)
    private Status status;
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private String createdAt;
    @LastModifiedDate
    @Column(name = "updated_at")
    private String updatedAt;
}