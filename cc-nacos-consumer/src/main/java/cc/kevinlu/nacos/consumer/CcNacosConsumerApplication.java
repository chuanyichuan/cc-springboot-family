package cc.kevinlu.nacos.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CcNacosConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CcNacosConsumerApplication.class, args);
    }

}
