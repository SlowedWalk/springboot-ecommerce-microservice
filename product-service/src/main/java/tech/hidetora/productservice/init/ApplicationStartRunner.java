package tech.hidetora.productservice.init;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tech.hidetora.productservice.enumeration.CategoryName;
import tech.hidetora.productservice.enumeration.Status;
import tech.hidetora.productservice.model.Product;
import tech.hidetora.productservice.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class ApplicationStartRunner implements CommandLineRunner {

    private final ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
      log.info("############ Application starting ############");
      Product p1 = Product.builder()
              .name("Product 1")
              .description("Product 1 description")
              .price(BigDecimal.valueOf(100.00))
              .category(CategoryName.HOME)
              .status(Status.IN_STOCK)
              .quantity(20)
              .imageUrl("https://dummyimage.com/241x100.png/dddddd/000000")
              .build();
      Product p2 = Product.builder()
              .name("Product 2")
              .description("Product 2 description")
              .price(BigDecimal.valueOf(200.00))
              .category(CategoryName.ELECTRONICS)
              .status(Status.OUT_OF_STOCK)
              .quantity(50)
              .imageUrl("https://dummyimage.com/170x100.png/cc0000/ffffff")
              .build();
      Product p3 = Product.builder()
              .name("Product 3")
              .description("Product 3 description")
              .price(BigDecimal.valueOf(300.00))
              .category(CategoryName.HEALTH)
              .status(Status.IN_STOCK)
              .quantity(8)
              .imageUrl("https://dummyimage.com/198x100.png/5fa2dd/ffffff")
              .build();
      Product p4 = Product.builder()
              .name("Product 4")
              .description("Product 4 description")
              .price(BigDecimal.valueOf(400.00))
              .category(CategoryName.HOME)
              .status(Status.IN_STOCK)
              .quantity(2)
              .imageUrl("https://dummyimage.com/166x100.png/5fa2dd/ffffff")
              .build();
      Product p5 = Product.builder()
              .name("Product 5")
              .description("Product 5 description")
              .price(BigDecimal.valueOf(500.00))
              .category(CategoryName.TOYS)
              .status(Status.COMING_SOON)
              .quantity(5)
              .imageUrl("https://dummyimage.com/232x100.png/dddddd/000000")
              .build();
      Product p6 = Product.builder()
              .name("Product 6")
              .description("Product 6 description")
              .price(BigDecimal.valueOf(600.00))
              .category(CategoryName.SPORTS)
              .status(Status.IN_STOCK)
              .quantity(10)
              .imageUrl("https://dummyimage.com/225x100.png/cc0000/ffffff")
              .build();

      productRepository.saveAll(List.of(p1,p2,p3,p4,p5,p6));
      log.info("############ Application Running ############");
    }
}
