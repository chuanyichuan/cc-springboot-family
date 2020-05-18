package com.example.springcloudconsumerfeign.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springcloudconsumerfeign.service.IFeignService;

@RestController
@RequestMapping("/consumer")
public class FeignController {

    @Autowired
    private IFeignService feignService;

    @GetMapping("/getUser")
    public String getUser(Integer id) {
        return feignService.getUser(id);
    }
}
