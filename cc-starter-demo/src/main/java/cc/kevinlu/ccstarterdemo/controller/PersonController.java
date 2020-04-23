package cc.kevinlu.ccstarterdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import cc.kevinlu.ccspringbootstarterautoconfigurer.wt.CcService;

@RestController
public class PersonController {

    @Autowired
    private CcService ccService;

    @GetMapping("/info")
    public void info() {
        System.out.println(ccService.info());
    }

}
