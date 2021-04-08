package cc.kevinlu.deferred.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cc.kevinlu.deferred.service.TimeServiceImpl;

@RestController
public class RController {

    @Resource
    private TimeServiceImpl timeService;

    @RequestMapping("/index")
    public String hello() {
        timeService.schedule();
        return "";
    }

}
