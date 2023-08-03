package tech.hidetora.orderservice.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tech.hidetora.orderservice.dto.reponse.ApiResponse;
import tech.hidetora.orderservice.dto.request.OrderRequest;
import tech.hidetora.orderservice.service.OrderService;

import java.time.Instant;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @PostMapping("/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name="inventory", fallbackMethod="palceOrderFallback") // Circuit breaker annotation to enable circuit breaker on this method
    @TimeLimiter(name="inventory", fallbackMethod="palceOrderFallback") // Time limiter annotation to enable time limiter on this method
    @Retry(name="inventory")
    public CompletableFuture<ApiResponse> placeOrder(@RequestBody OrderRequest orderRequest, @PathVariable String userId) {
        service.placeOrder(orderRequest, userId);

        return CompletableFuture.supplyAsync(() -> ApiResponse.builder()
                .message("Order placed successfully")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.CREATED)
                .statusCode(HttpStatus.CREATED.value())
                .timestamp(Instant.now().toString())
                .data("Order placed successfully")
                .build());
    }

    // Fallback method to be called when circuit breaker is open
    public CompletableFuture<ApiResponse> palceOrderFallback() {

        return CompletableFuture.supplyAsync(() -> ApiResponse.builder()
                .message("Oops! Something went wrong. Please try again later.")
                .success(Boolean.FALSE)
                .httpStatus(HttpStatus.GATEWAY_TIMEOUT)
                .statusCode(HttpStatus.GATEWAY_TIMEOUT.value())
                .timestamp(Instant.now().toString())
                .data("Oops! Something went wrong. Please try again later.")
                .build());
    }
}
