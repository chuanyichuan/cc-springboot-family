package cc.kevinlu.deferred;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

public class SpringApplicationSelf extends SpringApplication {

    @Override
    protected void afterRefresh(ConfigurableApplicationContext context, ApplicationArguments args) {
        System.out.println("------afterRefresh-----");
    }
}
