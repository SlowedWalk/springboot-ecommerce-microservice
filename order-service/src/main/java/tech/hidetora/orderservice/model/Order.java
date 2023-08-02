package tech.hidetora.orderservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import tech.hidetora.orderservice.enumeration.OrderStatus;

import java.util.List;
import java.util.UUID;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.UUID;

@Data
@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Order {
    @Id
    @GeneratedValue(strategy = UUID)
    private UUID id;

//    @Column(unique = true, updatable = false)
    private String orderNumber;

    @Enumerated(STRING)
    private OrderStatus orderStatus;

    @Column(name = "order_total")
    private double orderTotal;

    @Column(name = "user_id")
    private String userId;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private String orderDate;

    @LastModifiedDate
    @Column(name = "updated_at")
    private String updatedAt;
}
