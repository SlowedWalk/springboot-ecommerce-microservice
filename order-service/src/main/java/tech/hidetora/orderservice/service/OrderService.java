package tech.hidetora.orderservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.hidetora.orderservice.dto.request.OrderItemRequest;
import tech.hidetora.orderservice.dto.request.OrderRequest;
import tech.hidetora.orderservice.enumeration.OrderStatus;
import tech.hidetora.orderservice.model.Order;
import tech.hidetora.orderservice.model.OrderItem;
import tech.hidetora.orderservice.repository.OrderRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository repository;

    public void placeOrder(OrderRequest request, String userId) {
        List<OrderItem> orderItems = request.getOrderItems()
                .stream()
                .map(OrderItemRequest::toOrderItem)
                .toList();

        double orderTotal = 0;
        for (OrderItem orderItem : orderItems) {
            orderTotal = orderItem.getQuantity() * orderItem.getProductPrice();
        }

        Order order = Order.builder()
                .orderStatus(OrderStatus.PENDING)
                .orderNumber(UUID.randomUUID().toString().substring(0,8))
                .orderItems(orderItems)
                .userId(userId)
                .orderTotal(orderTotal)
                .build();

        repository.save(order);
    }
}
