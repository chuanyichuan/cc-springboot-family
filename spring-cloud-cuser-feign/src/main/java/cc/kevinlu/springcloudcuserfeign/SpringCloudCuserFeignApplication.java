package cc.kevinlu.springcloudcuserfeign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

//@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class SpringCloudCuserFeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudCuserFeignApplication.class, args);
    }

}
