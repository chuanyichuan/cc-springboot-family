package cc.kevinlu.account.action;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

@LocalTCC
public interface AccountTccAction {

    @TwoPhaseBusinessAction(name = "accountTccAction")
    boolean prepare(BusinessActionContext context, @BusinessActionContextParameter(paramName = "userId") String userId,
                    @BusinessActionContextParameter(paramName = "m") int m);

    boolean commit(BusinessActionContext context);

    boolean rollback(BusinessActionContext context);

}
