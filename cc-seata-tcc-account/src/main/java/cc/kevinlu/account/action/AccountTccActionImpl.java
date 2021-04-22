package cc.kevinlu.account.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cc.kevinlu.account.holder.ResultHolder;
import cc.kevinlu.account.service.AccountService;
import io.seata.rm.tcc.api.BusinessActionContext;

@Service
public class AccountTccActionImpl implements AccountTccAction {

    @Resource
    private AccountService accountService;

    @Override
    public boolean prepare(BusinessActionContext context, String userId, int m) {
        try {
            if (!accountService.decrMoney(userId, m)) {
                throw new RuntimeException("account error");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        ResultHolder.setResult(AccountTccAction.class, context.getXid(), "p");
        return true;
    }

    @Override
    public boolean commit(BusinessActionContext context) {
        if (null == ResultHolder.getResult(AccountTccAction.class, context.getXid())) {
            return true;
        }

        ResultHolder.removeResult(AccountTccAction.class, context.getXid());
        return true;
    }

    @Override
    public boolean rollback(BusinessActionContext context) {
        if (null == ResultHolder.getResult(AccountTccAction.class, context.getXid())) {
            return true;
        }

        String userId = context.getActionContext("userId").toString();
        int m = Integer.parseInt(context.getActionContext("m").toString());

        if (!accountService.decrMoney(userId, -m)) {
            return false;
        }
        ResultHolder.removeResult(AccountTccAction.class, context.getXid());
        return true;
    }
}
