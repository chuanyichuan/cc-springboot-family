package cc.kevinlu.nacos1.service;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cc.kevinlu.nacos1.clients.AccountFeign;
import cc.kevinlu.nacos1.clients.StorageFeign;
import cc.kevinlu.nacos1.data.mapper.OrderMapper;
import cc.kevinlu.nacos1.data.model.OrderDO;
import io.seata.spring.annotation.GlobalTransactional;

@Service
public class OrderService {

    @Resource
    private OrderMapper  orderMapper;
    @Resource
    private AccountFeign accountFeign;
    @Resource
    private StorageFeign storageFeign;

    // (rollbackFor = Exception.class, timeoutMills = 300000, name = "spring-cloud-demo-tx")
    @GlobalTransactional
    @Transactional(rollbackFor = Exception.class)
    public boolean order(String userId, String commodityCode, int count, int s) {
        if (count <= 0) {
            throw new RuntimeException("params illegal!");
        }
        BigDecimal price = storageFeign.price(commodityCode);
        int m = price.multiply(BigDecimal.valueOf(count)).intValue();

        OrderDO order = new OrderDO();
        order.setUserId(userId);
        order.setCommodityCode(commodityCode);
        order.setCount(count);
        order.setMoney(m);
        int ct = orderMapper.insertSelective(order);

        accountFeign.money(userId, m);
        storageFeign.storage(commodityCode, count);
        if (s == 0) {
            throw new RuntimeException("hahahhaha!");
        }
        return ct == 1;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean orderNormal(String userId, String commodityCode, int count, int m) {
        BigDecimal price = storageFeign.price(commodityCode);
        m = price.multiply(BigDecimal.valueOf(count)).intValue();

        OrderDO order = new OrderDO();
        order.setUserId(userId);
        order.setCommodityCode(commodityCode);
        order.setCount(count);
        order.setMoney(m);
        int ct = orderMapper.insertSelective(order);
        return ct == 1;
    }

}
