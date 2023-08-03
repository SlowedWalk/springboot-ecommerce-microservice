package tech.hidetora.inventoryservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.hidetora.inventoryservice.repository.InventoryRepository;
import tech.hidetora.inventoryservice.dto.response.InventoryResponse;
import tech.hidetora.inventoryservice.model.Inventory;

import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository repository;
    
        public List<InventoryResponse> isInStock(List<String> productIds) {
            log.info("Checking stock for products: {}", productIds);
            List<Inventory> productIdIn = repository.findByProductIdIn(productIds);
            return productIdIn.stream().map(inventory -> InventoryResponse.builder()
                        .productId(inventory.getProductId())
                        .isInStock(inventory.getQuantity() > 0)
                        .build()
            ).toList();
        }
}
