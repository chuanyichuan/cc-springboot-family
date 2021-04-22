package cc.kevinlu.order.clients;

import java.math.BigDecimal;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "storage")
public interface StorageFeign {

    @GetMapping("/storage/des")
    boolean storage(@RequestParam("commodityCode") String commodityCode, @RequestParam("count") int count);

    @GetMapping("/storage/price")
    BigDecimal price(@RequestParam("commodityCode") String commodityCode);

}
