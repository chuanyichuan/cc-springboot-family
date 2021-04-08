package cc.kevinlu.caffeine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class CcSpringBootCaffeineApplication {

    public static void main(String[] args) {
        SpringApplication.run(CcSpringBootCaffeineApplication.class, args);
    }

}
