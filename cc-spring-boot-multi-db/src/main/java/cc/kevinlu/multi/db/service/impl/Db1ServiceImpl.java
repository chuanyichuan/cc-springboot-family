package cc.kevinlu.multi.db.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import cc.kevinlu.multi.db.dao.mapper.bugfree.BugsMapper;
import cc.kevinlu.multi.db.dao.model.source1.BugsDO;
import cc.kevinlu.multi.db.service.Db1Service;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class Db1ServiceImpl implements Db1Service {

    @Resource
    private BugsMapper bugsMapper;

    @Override
    public void test() {
        BugsDO data = bugsMapper.selectByPrimaryKey(1);
        log.info("data = [{}]", JSONObject.toJSONString(data));
    }
}
