package com.example.springbootlog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/test")
    public String testLogin() {
        log.info("test成功！");
        return "ok";
    }

}
