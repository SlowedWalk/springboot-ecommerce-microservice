package tech.hidetora.orderservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class WelcomeController {
    @GetMapping
    public String welcome() {
        return """
                    Welcome to the Order Service, the service is up and running.\n
                    Please use the API documentation to use the API.\n
                    The documentation can be found at: http://locahost:8080/swagger-ui.html
                    """;
    }
}
