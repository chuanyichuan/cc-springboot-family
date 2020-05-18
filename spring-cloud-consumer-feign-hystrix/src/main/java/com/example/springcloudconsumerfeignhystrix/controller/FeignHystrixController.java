package com.example.springcloudconsumerfeignhystrix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springcloudconsumerfeignhystrix.service.UserFeignService;

@RestController
@RequestMapping("/hystrix/consumer")
public class FeignHystrixController {

    @Autowired
    private UserFeignService userFeignService;

    @GetMapping("/getUser")
    public String getUser(Integer id) {
        return userFeignService.getUser(id);
    }
}
