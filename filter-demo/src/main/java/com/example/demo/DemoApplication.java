package com.example.demo;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;

import com.example.demo.filter.MyFilter2;

@SpringBootApplication
@ServletComponentScan(basePackages = "com.example.demo.filter")
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean doFilterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(new MyFilter2());
        registrationBean.setName("myFilter2");
        registrationBean.setUrlPatterns(Arrays.asList("/test"));
        return registrationBean;
    }

}
