package cc.kevinlu.account;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@MapperScan(basePackages = "cc.kevinlu.nacos1.data.mapper")
public class CcNacosClient1Application {

    public static void main(String[] args) {
        SpringApplication.run(CcNacosClient1Application.class, args);
    }

}
