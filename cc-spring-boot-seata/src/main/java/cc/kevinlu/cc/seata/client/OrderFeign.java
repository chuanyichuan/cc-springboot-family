package cc.kevinlu.cc.seata.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "order")
public interface OrderFeign {

    @RequestMapping("/order/d")
    boolean order(@RequestParam("userId") String userId, @RequestParam("commodityId") String commodityId,
                  @RequestParam("count") int count, @RequestParam("m") int m);

    @GetMapping("/order/d2")
    boolean orderNormal(@RequestParam String userId, @RequestParam String commodityId, @RequestParam int count,
                        @RequestParam int m);

}
