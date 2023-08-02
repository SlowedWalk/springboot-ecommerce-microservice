package tech.hidetora.orderservice.init;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ApplicationStartRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        log.info("############ Application starting ############");

        log.info("############ Application started ############");
    }
}

