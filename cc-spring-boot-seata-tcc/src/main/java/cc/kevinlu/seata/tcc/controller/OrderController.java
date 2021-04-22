package cc.kevinlu.seata.tcc.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cc.kevinlu.seata.tcc.service.OrderService;

@RequestMapping("/order")
@RestController
public class OrderController {

    @Resource
    private OrderService orderService;

    @GetMapping("/ding")
    public String order(String userId, String commodityCode, int count) {
        return orderService.order(userId, commodityCode, count);
    }

    @GetMapping("/tcc")
    public boolean orderTcc(String userId, String commodityCode, int count) {
        return orderService.orderTcc(userId, commodityCode, count);
    }

}
