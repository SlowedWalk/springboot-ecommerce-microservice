package tech.hidetora.inventoryservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.hidetora.inventoryservice.model.Inventory;
import tech.hidetora.inventoryservice.repository.InventoryRepository;

import java.util.Optional;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository repository;
    
        public boolean isInStock(String productId) {
            log.info("Checking stock for product id: {}", productId);
            Optional<Inventory> byProductId = repository.findByProductId(productId);

            return byProductId.isPresent();
        }
}
