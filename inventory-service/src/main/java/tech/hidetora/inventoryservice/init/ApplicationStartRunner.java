package tech.hidetora.inventoryservice.init;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tech.hidetora.inventoryservice.model.Inventory;
import tech.hidetora.inventoryservice.repository.InventoryRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class ApplicationStartRunner implements CommandLineRunner {

    private final InventoryRepository repository;

    @Override
    public void run(String... args) throws Exception {
        log.info("############ Application starting ############");

        Inventory inventory = Inventory.builder()
                .quantity(10)
                .productId("1")
                .build();
        Inventory inventory2 = Inventory.builder()
                .quantity(20)
                .productId("2")
                .build();
        Inventory inventory3 = Inventory.builder()
                .quantity(0)
                .productId("3")
                .build();
        Inventory inventory4 = Inventory.builder()
                .quantity(5)
                .productId("4")
                .build();

        repository.saveAll(List.of(inventory, inventory2, inventory3, inventory4));

        log.info("############ Application started ############");
    }
}
