package tech.hidetora.orderservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tech.hidetora.orderservice.dto.reponse.ApiResponse;
import tech.hidetora.orderservice.dto.request.OrderRequest;
import tech.hidetora.orderservice.service.OrderService;

import java.time.Instant;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @PostMapping("/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse placeOrder(@RequestBody OrderRequest orderRequest, @PathVariable String userId) {
        service.placeOrder(orderRequest, userId);

        return ApiResponse.builder()
                .message("Order placed successfully")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.CREATED)
                .statusCode(HttpStatus.CREATED.value())
                .timestamp(Instant.now().toString())
                .data("Order placed successfully")
                .build();
    }
}
