package cc.kevinlu.multi.db;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "cc.kevinlu.multi.db")
public class CcSpringBootMultiDbApplication {

    public static void main(String[] args) {
        SpringApplication.run(CcSpringBootMultiDbApplication.class, args);
    }

}
