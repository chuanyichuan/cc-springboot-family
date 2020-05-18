package cc.kevinlu.springcloudcuserconsumerhystrix.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@RestController
public class UserController {

    @Resource
    RestTemplate restTemplate;

    static int   index = 0;

    @HystrixCommand(fallbackMethod = "faultMethod", threadPoolProperties = {
            @HystrixProperty(name = "coreSize", value = "2"),
            @HystrixProperty(name = "queueSizeRejectionThreshold", value = "2"),
            @HystrixProperty(name = "maxQueueSize", value = "10") }, commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000") })
    @GetMapping("/cuser")
    public String getUser() {
        try {
            index++;
            if (index % 3 == 0) {
                Thread.sleep(3000);
            }
        } catch (InterruptedException e) {
        }
        String obj = restTemplate.getForObject("http://cuser-service/user/1", String.class);
        return obj;
    }

    public String faultMethod() {
        return "服务熔断";
    }

}
