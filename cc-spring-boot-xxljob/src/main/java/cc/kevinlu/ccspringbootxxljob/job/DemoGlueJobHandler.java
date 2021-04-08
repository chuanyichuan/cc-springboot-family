package cc.kevinlu.ccspringbootxxljob.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;

public class DemoGlueJobHandler extends IJobHandler {
    private static transient Logger log = LoggerFactory.getLogger(DemoGlueJobHandler.class);

    @Override
    public ReturnT<String> execute(String s) throws Exception {
        log.info("CC GLUE JOB start success!");
        return ReturnT.SUCCESS;
    }
}
