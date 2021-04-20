package cc.kevinlu.nacos.consumer.service;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cc.kevinlu.nacos.consumer.AccountFeign;
import cc.kevinlu.nacos.consumer.OrderFeign;
import cc.kevinlu.nacos.consumer.StorageFeign;

@Service
public class OrderService {

    @Resource
    private AccountFeign accountFeign;
    @Resource
    private OrderFeign   orderFeign;
    @Resource
    private StorageFeign storageFeign;

    public String order(String userId, String commodityCode, int count) {
        BigDecimal price = storageFeign.price(commodityCode);
        int m = price.multiply(BigDecimal.valueOf(count)).intValue();

        boolean ob = orderFeign.order(userId, commodityCode, count, m);
        if (!ob) {
            throw new RuntimeException("order error one!");
        }

        ob = accountFeign.money(userId, m);
        if (!ob) {
            throw new RuntimeException("order error two!");
        }

        ob = storageFeign.storage(commodityCode, count);
        if (!ob) {
            throw new RuntimeException("order error three!");
        }

        return "ok!";
    }

}
