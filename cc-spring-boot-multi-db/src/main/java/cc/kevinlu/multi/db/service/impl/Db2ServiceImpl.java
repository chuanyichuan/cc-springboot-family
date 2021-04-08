package cc.kevinlu.multi.db.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import cc.kevinlu.multi.db.dao.mapper.codeac.UserMapper;
import cc.kevinlu.multi.db.dao.model.source2.User;
import cc.kevinlu.multi.db.service.Db2Service;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class Db2ServiceImpl implements Db2Service {

    @Resource
    private UserMapper userMapper;

    @Override
    public void test() {
        List<User> list = userMapper.selectAll();
        log.info("list = [{}]", JSONObject.toJSONString(list));
    }
}
