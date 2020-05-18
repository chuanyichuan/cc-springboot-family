package cc.kevinlu.image.extractor.events.order;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Resource
    private OrderService orderService;

    @GetMapping("/order")
    public String order() {
        orderService.order();
        return "success";
    }

}
