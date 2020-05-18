package cc.kevinlu.springcloudconsul.health;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/health")
    public String health() {
        System.out.println("----health check----");
        return "hello consul";
    }

}
