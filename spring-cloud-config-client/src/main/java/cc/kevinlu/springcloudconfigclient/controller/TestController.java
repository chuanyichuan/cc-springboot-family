package cc.kevinlu.springcloudconfigclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
@RequestMapping("/index")
public class TestController {

    @Value("${name}")
    private String name;

    @GetMapping("/one")
    public String one() {
        System.out.println(name);
        return name;
    }

}
