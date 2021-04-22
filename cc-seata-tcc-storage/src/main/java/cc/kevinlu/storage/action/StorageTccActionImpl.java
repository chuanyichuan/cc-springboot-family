package cc.kevinlu.storage.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cc.kevinlu.storage.holder.ResultHolder;
import cc.kevinlu.storage.service.StorageService;
import io.seata.rm.tcc.api.BusinessActionContext;

@Service
public class StorageTccActionImpl implements StorageTccAction {

    @Resource
    private StorageService storageService;

    @Override
    public boolean prepare(BusinessActionContext ctx, String commodityCode, int count) {
        try {
            if (!storageService.storage(commodityCode, count)) {
                throw new RuntimeException("storage error!");
            }
        } catch (RuntimeException e) {
            throw e;
        }

        ResultHolder.setResult(StorageTccAction.class, ctx.getXid(), "p");
        return true;
    }

    @Override
    public boolean commit(BusinessActionContext ctx) {
        if (null == ResultHolder.getResult(StorageTccAction.class, ctx.getXid())) {
            return true;
        }
        ResultHolder.removeResult(StorageTccAction.class, ctx.getXid());
        return true;
    }

    @Override
    public boolean rollback(BusinessActionContext ctx) {
        if (null == ResultHolder.getResult(StorageTccAction.class, ctx.getXid())) {
            return true;
        }

        String commodityCode = ctx.getActionContext("commodityCode").toString();
        int count = Integer.valueOf(ctx.getActionContext("count").toString());

        if (!storageService.storage(commodityCode, -count)) {
            return false;
        }

        ResultHolder.removeResult(StorageTccAction.class, ctx.getXid());
        return true;
    }
}
