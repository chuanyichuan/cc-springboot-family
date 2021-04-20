package cc.kevinlu.nacos.consumer;

import java.math.BigDecimal;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "storage")
public interface StorageFeign {

    @GetMapping("/des")
    boolean storage(String commodityCode, int count);

    @GetMapping("/price")
    BigDecimal price(String commodityCode);

}
