package cc.kevinlu.shardingjdbc;

import org.apache.shardingsphere.shardingjdbc.spring.boot.SpringBootConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class, SpringBootConfiguration.class })
public class CcSpringBootShardingjdbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(CcSpringBootShardingjdbcApplication.class, args);
    }

}
