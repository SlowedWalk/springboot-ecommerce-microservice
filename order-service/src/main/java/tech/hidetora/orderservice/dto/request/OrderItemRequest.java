package tech.hidetora.orderservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.hidetora.orderservice.model.OrderItem;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemRequest {
    private String id;
    private String productId;
    private String orderNumber;
    private double quantity;
    private double productPrice;
    private double orderTotal;

    public static OrderItemRequest toOrderItemDto(OrderItem orderItem) {
        return OrderItemRequest.builder()
                .orderNumber(orderItem.getOrderNumber())
                .quantity(orderItem.getQuantity())
                .productPrice(orderItem.getProductPrice())
                .orderTotal(orderItem.getProductPrice() * orderItem.getQuantity())
                .build();
    }

    public static OrderItem toOrderItem(OrderItemRequest orderItemRequest) {
        return OrderItem.builder()
                .orderNumber(orderItemRequest.getOrderNumber())
                .quantity(orderItemRequest.getQuantity())
                .productPrice(orderItemRequest.getProductPrice())
                .orderTotal(orderItemRequest.getQuantity() * orderItemRequest.getProductPrice())
                .build();
    }
}
