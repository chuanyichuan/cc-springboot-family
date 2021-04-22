package cc.kevinlu.seata.tcc.action;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cc.kevinlu.seata.tcc.client.AccountFeign;
import cc.kevinlu.seata.tcc.client.OrderFeign;
import cc.kevinlu.seata.tcc.client.StorageFeign;
import cc.kevinlu.seata.tcc.service.OrderService;
import io.seata.rm.tcc.api.BusinessActionContext;

@Service
public class SeataTccActionImpl implements SeataTccAction {

    @Resource
    private OrderService orderService;
    @Resource
    private AccountFeign accountFeign;
    @Resource
    private OrderFeign   orderFeign;
    @Resource
    private StorageFeign storageFeign;

    @Override
    public boolean prepare(BusinessActionContext ctx, String userId, String commodityCode, int count) {
        try {
            BigDecimal price = storageFeign.price(commodityCode);
            int m = price.multiply(BigDecimal.valueOf(count)).intValue();

            orderFeign.orderTcc(userId, commodityCode, count);
            accountFeign.money(userId, m);
            storageFeign.storageTcc(commodityCode, count);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return true;
    }

    @Override
    public boolean commit(BusinessActionContext ctx) {
        return true;
    }

    @Override
    public boolean rollback(BusinessActionContext ctx) {
        return true;
    }
}
