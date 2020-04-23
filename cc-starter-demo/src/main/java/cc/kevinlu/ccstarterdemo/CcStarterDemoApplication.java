package cc.kevinlu.ccstarterdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import cc.kevinlu.ccspringbootstarterautoconfigurer.wt.CcProperties;
import cc.kevinlu.ccspringbootstarterautoconfigurer.wt.CcService;

@SpringBootApplication
public class CcStarterDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CcStarterDemoApplication.class, args);
    }

    @Bean
    public CcService ccService(CcProperties ccProperties) {
        CcService ccService = new CcService();
        System.out.println("====client register====");
        ccService.setCcProperties(ccProperties);
        return ccService;
    }

}
