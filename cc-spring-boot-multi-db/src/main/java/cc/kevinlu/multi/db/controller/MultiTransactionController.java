package cc.kevinlu.multi.db.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import cc.kevinlu.multi.db.service.MultiDbService;

@RestController
public class MultiTransactionController {

    @Resource
    private MultiDbService multiDbService;

    @GetMapping("/save/no")
    public void testNo() {
        multiDbService.saveMultiNoTransaction();
    }

    @GetMapping("/save/one")
    public void testOne() {
        multiDbService.saveMultiWithOneTransaction();
    }

    @GetMapping("/save/two")
    public void testTwo() {
        multiDbService.saveMultiWithTwoTransaction();
    }

    @GetMapping("/save/all")
    public void testAll() {
        multiDbService.saveMultiWithMultiTransaction();
    }

}
