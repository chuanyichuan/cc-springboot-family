package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

/**
 * 消费者
 */
@Slf4j
@RestController
@RequestMapping("/consumer")
public class RibbonConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 调用 user微服务
     */
    @GetMapping("getUser")
    public String getUser(Integer id) {
        String url = "http://user-service2/provider/getUser?id=" + id;
        return restTemplate.getForObject(url, String.class);
    }

}
