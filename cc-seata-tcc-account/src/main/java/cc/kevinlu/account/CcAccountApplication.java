package cc.kevinlu.account;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "cc.kevinlu.account.data.mapper")
public class CcAccountApplication {

    public static void main(String[] args) {
        SpringApplication.run(CcAccountApplication.class, args);
    }

}
