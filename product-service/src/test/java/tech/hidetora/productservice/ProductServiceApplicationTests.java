package tech.hidetora.productservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import tech.hidetora.productservice.enumeration.CategoryName;
import tech.hidetora.productservice.enumeration.Status;
import tech.hidetora.productservice.model.Product;
import tech.hidetora.productservice.repository.ProductRepository;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductServiceApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProductRepository productRepository;

    @Container
    @ServiceConnection
    private static final MySQLContainer mySQLContainer = new MySQLContainer<>("mysql:latest")
            .withDatabaseName("product-service-db")
            .withUsername("admin")
            .withPassword("password");

    @Test
    @Order(1)
    void testMySQLContainerIsRunning() {
        assertTrue(mySQLContainer.isRunning());
    }

    @Test
    @Order(2)
    void shouldCreateProduct() throws Exception {
        Product productRequest = getProductRequest();

        String requestBody = objectMapper.writeValueAsString(productRequest);

        mockMvc
                .perform(MockMvcRequestBuilders.post("/api/v1/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated());

        assertEquals(7, productRepository.findAll().size());
    }

    private Product getProductRequest() {
        return Product.builder()
                .name("Test Product 1")
                .description("Test Product 1 description")
                .price(BigDecimal.valueOf(100.00))
                .category(CategoryName.HOME)
                .status(Status.IN_STOCK)
                .quantity(20)
                .imageUrl("https://dummyimage.com/241x100.png/dddddd/000000")
                .build();
    }

}
