package cc.kevinlu.cc.seata.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cc.kevinlu.cc.seata.service.OrderService;

@RequestMapping("/order")
@RestController
public class OrderController {

    @Resource
    private OrderService orderService;

    @GetMapping("/ding")
    public String order(String userId, String commodityCode, int count) {
        return orderService.order(userId, commodityCode, count);
    }

}
