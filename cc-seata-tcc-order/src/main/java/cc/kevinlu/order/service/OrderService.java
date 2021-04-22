package cc.kevinlu.order.service;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cc.kevinlu.order.action.OrderTccAction;
import cc.kevinlu.order.clients.AccountFeign;
import cc.kevinlu.order.clients.StorageFeign;
import cc.kevinlu.order.data.mapper.OrderMapper;
import cc.kevinlu.order.data.model.OrderDO;
import io.seata.spring.annotation.GlobalTransactional;

@Service
public class OrderService {

    @Resource
    private OrderMapper    orderMapper;
    @Resource
    private AccountFeign   accountFeign;
    @Resource
    private StorageFeign   storageFeign;

    @Resource
    private OrderTccAction orderTccAction;

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
    public Integer orderNormal(String userId, String commodityCode, int count) {
        //        BigDecimal price = storageFeign.price(commodityCode);
        //        int m = price.multiply(BigDecimal.valueOf(count)).intValue();
        int m = 1;

        OrderDO order = new OrderDO();
        order.setUserId(userId);
        order.setCommodityCode(commodityCode);
        order.setCount(count);
        order.setMoney(m);
        int ct = orderMapper.insertSelective(order);
        return order.getId();
    }

    @GlobalTransactional
    public boolean orderTcc(String userId, String commodityCode, int count) {
        if (!orderTccAction.prepare(null, userId, commodityCode, count)) {
            throw new RuntimeException("create order error!");
        }
        return true;
    }

    public void deleteById(int id) {
        orderMapper.deleteByPrimaryKey(id);
    }
}
