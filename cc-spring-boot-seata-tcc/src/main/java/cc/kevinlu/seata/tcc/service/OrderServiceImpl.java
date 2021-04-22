package cc.kevinlu.seata.tcc.service;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cc.kevinlu.seata.tcc.action.SeataTccAction;
import cc.kevinlu.seata.tcc.client.AccountFeign;
import cc.kevinlu.seata.tcc.client.OrderFeign;
import cc.kevinlu.seata.tcc.client.StorageFeign;
import io.seata.spring.annotation.GlobalTransactional;

@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private AccountFeign   accountFeign;
    @Resource
    private OrderFeign     orderFeign;
    @Resource
    private StorageFeign   storageFeign;

    @Resource
    private SeataTccAction seataTccAction;

    @Override
    @GlobalTransactional(rollbackFor = Exception.class, timeoutMills = 300000, name = "spring-cloud-demo-tx")
    @Transactional(rollbackFor = Exception.class)
    public String order(String userId, String commodityCode, int count) {
        BigDecimal price = storageFeign.price(commodityCode);
        int m = price.multiply(BigDecimal.valueOf(count)).intValue();

        boolean ob = orderFeign.orderNormal(userId, commodityCode, count, m);
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

    @Override
    @GlobalTransactional
    public boolean orderTcc(String userId, String commodityCode, int count) {
        return seataTccAction.prepare(null, userId, commodityCode, count);
    }

}
