package cc.kevinlu.seata.tcc.action;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

@LocalTCC
public interface SeataTccAction {

    @TwoPhaseBusinessAction(name = "seataTccAction")
    boolean prepare(BusinessActionContext ctx, @BusinessActionContextParameter(paramName = "userId") String userId,
                    @BusinessActionContextParameter(paramName = "commodityCode") String commodityCode,
                    @BusinessActionContextParameter(paramName = "count") int count);

    boolean commit(BusinessActionContext ctx);

    boolean rollback(BusinessActionContext ctx);

}
