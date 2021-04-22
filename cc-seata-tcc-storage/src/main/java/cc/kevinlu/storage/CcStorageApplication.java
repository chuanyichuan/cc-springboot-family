package cc.kevinlu.storage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "cc.kevinlu.storage.data.mapper")
public class CcStorageApplication {

    public static void main(String[] args) {
        SpringApplication.run(CcStorageApplication.class, args);
    }

}
