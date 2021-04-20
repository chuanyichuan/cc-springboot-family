package cc.kevinlu.nacos1;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cc.kevinlu.nacos1.service.OrderService;

@RequestMapping("/order")
@RestController
public class OrderController {

    @Resource
    private OrderService orderService;

    @GetMapping("/d")
    public boolean order(@RequestParam String userId, @RequestParam String commodityId, @RequestParam int count,
                         @RequestParam int m) {
        return orderService.order(userId, commodityId, count, m);
    }

    @GetMapping("/d2")
    public boolean orderNormal(@RequestParam String userId, @RequestParam String commodityId, @RequestParam int count,
                               @RequestParam int m) {
        return orderService.orderNormal(userId, commodityId, count, m);
    }

}
