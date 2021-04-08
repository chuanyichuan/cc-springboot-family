package cc.kevinlu.multi.db.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import cc.kevinlu.multi.db.service.Db1Service;

@RestController
public class Db1Controller {

    @Resource
    private Db1Service db1Service;

    @GetMapping("/test1")
    public void test1() {
        db1Service.test();
    }

}
