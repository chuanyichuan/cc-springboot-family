package cc.kevinlu.multi.db.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import cc.kevinlu.multi.db.service.Db2Service;

@RestController
public class Db2Controller {

    @Resource
    private Db2Service db2Service;

    @GetMapping("/test2")
    public void test1() {
        db2Service.test();
    }

}
