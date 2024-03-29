package cc.kevinlu.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@MapperScan(basePackages = "cc.kevinlu.order.data.mapper")
public class CcOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(CcOrderApplication.class, args);
    }

}
