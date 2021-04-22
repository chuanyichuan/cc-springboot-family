package cc.kevinlu.order.action;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import cc.kevinlu.order.holder.ResultHolder;
import cc.kevinlu.order.service.OrderService;
import io.seata.rm.tcc.api.BusinessActionContext;

@Service
public class OrderTccActionImpl implements OrderTccAction {

    @Resource
    private OrderService orderService;

    @Override
    public boolean prepare(BusinessActionContext ctx, String userId, String commodityCode, int count) {
        Integer id = orderService.orderNormal(userId, commodityCode, count);
        if (id == null) {
            throw new RuntimeException("order error!");
        }
        ResultHolder.setResult(OrderTccAction.class, ctx.getXid(), "" + id);
        return true;
    }

    @Override
    public boolean commit(BusinessActionContext ctx) {
        if (null == ResultHolder.getResult(OrderTccAction.class, ctx.getXid())) {
            return true;
        }
        ResultHolder.removeResult(OrderTccAction.class, ctx.getXid());
        return true;
    }

    @Override
    public boolean rollback(BusinessActionContext ctx) {
        String id = ResultHolder.getResult(OrderTccAction.class, ctx.getXid());
        if (StringUtils.isBlank(id)) {
            return true;
        }
        orderService.deleteById(Integer.parseInt(id));
        ResultHolder.removeResult(OrderTccAction.class, ctx.getXid());
        return true;
    }
}
