package cc.kevinlu.storage.action;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

@LocalTCC
public interface StorageTccAction {

    @TwoPhaseBusinessAction(name = "storageTccAction")
    boolean prepare(BusinessActionContext ctx,
                    @BusinessActionContextParameter(paramName = "commodityCode") String commodityCode,
                    @BusinessActionContextParameter(paramName = "count") int count);

    boolean commit(BusinessActionContext ctx);

    boolean rollback(BusinessActionContext ctx);

}
