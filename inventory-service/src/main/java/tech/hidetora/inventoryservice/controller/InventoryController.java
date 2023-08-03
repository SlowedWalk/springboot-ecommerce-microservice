package tech.hidetora.inventoryservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tech.hidetora.inventoryservice.dto.response.ApiResponse;
import tech.hidetora.inventoryservice.dto.response.InventoryResponse;
import tech.hidetora.inventoryservice.service.InventoryService;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/v1/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse isInStock(@RequestParam List<String> productIds) {
        List<InventoryResponse> inStock = inventoryService.isInStock(productIds);
        return ApiResponse.builder()
                .timestamp(Instant.now().toString())
                .message("Inventory status for requested products")
                .data(inStock)
                .success(true)
                .httpStatus(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build();
    }
}
