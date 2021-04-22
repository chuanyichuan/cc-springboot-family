package cc.kevinlu.order.action;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

@LocalTCC
public interface OrderTccAction {

    @TwoPhaseBusinessAction(name = "orderTccAction")
    boolean prepare(BusinessActionContext ctx, @BusinessActionContextParameter(paramName = "userId") String userId,
                    @BusinessActionContextParameter(paramName = "commodityCode") String commodityCode,
                    @BusinessActionContextParameter(paramName = "count") int count);

    boolean commit(BusinessActionContext ctx);

    boolean rollback(BusinessActionContext ctx);

}
