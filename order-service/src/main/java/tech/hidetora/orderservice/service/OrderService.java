package tech.hidetora.orderservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import tech.hidetora.orderservice.enumeration.OrderStatus;
import tech.hidetora.orderservice.dto.reponse.InventoryResponse;
import tech.hidetora.orderservice.repository.OrderRepository;
import tech.hidetora.orderservice.dto.request.OrderItemRequest;
import tech.hidetora.orderservice.dto.request.OrderRequest;
import tech.hidetora.orderservice.model.Order;
import tech.hidetora.orderservice.model.OrderItem;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository repository;
    private final WebClient.Builder webClientBuilder;

    public void placeOrder(OrderRequest request, String userId) {
        List<OrderItem> orderItems = request.getOrderItems()
                .stream()
                .map(OrderItemRequest::toOrderItem)
                .toList();

        List<String> productIds = orderItems.stream().map(OrderItem::getProductId).toList();

        // call inventory service and place order if product is available
        InventoryResponse[] response = webClientBuilder.build()
                .get()
                .uri("http://inventory-service/api/v1/inventory",
                        uriBuilder -> uriBuilder
                                .queryParam("productIds", productIds)
                                .build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

        boolean allProductsInStock = Arrays.stream(response)
                .allMatch(InventoryResponse::isInStock);

        if (allProductsInStock) {
            double orderTotal = 0;
            //
            for (OrderItem orderItem : orderItems) {
                orderTotal = orderItem.getQuantity() * orderItem.getProductPrice();
            }

            Order order = Order.builder()
                    .orderStatus(OrderStatus.PENDING)
                    .orderNumber(UUID.randomUUID().toString().substring(0, 8))
                    .orderItems(orderItems)
                    .userId(userId)
                    .orderTotal(orderTotal)
                    .build();

            repository.save(order);
        }
    }
}
