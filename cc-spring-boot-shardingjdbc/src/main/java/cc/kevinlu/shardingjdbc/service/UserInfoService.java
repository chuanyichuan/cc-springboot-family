package cc.kevinlu.shardingjdbc.service;

import javax.annotation.Resource;

import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cc.kevinlu.shardingjdbc.entity.UserInfo;
import cc.kevinlu.shardingjdbc.mapper.UserInfoMapper;

@Service
public class UserInfoService {

    @Resource
    private UserInfoMapper userInfoMapper;

    public void insertSelective() {
        for (int i = 0; i < 10; i++) {
            UserInfo info = new UserInfo();
            String key = "abc" + i;
            info.setPassword(key);
            info.setAccount(key);
            info.setUserName(key);
            userInfoMapper.insertSelective(info);
        }
    }

    public void insert() {
        for (int i = 0; i < 10; i++) {
            UserInfo info = new UserInfo();
            String key = "abc" + i;
            info.setPassword(key);
            info.setAccount(key);
            info.setUserName(key);
            userInfoMapper.insert(info);
        }
    }

    @ShardingTransactionType(TransactionType.XA)
    @Transactional(rollbackFor = Exception.class)
    public void insertConflict() {
        UserInfo info = new UserInfo();
        String key = "abc";
        info.setUserId(122L);
        info.setPassword(key);
        info.setAccount(key);
        info.setUserName(key);
        userInfoMapper.insert(info);

        UserInfo info1 = new UserInfo();
        info1.setUserId(121L);
        info1.setPassword(key);
        info1.setAccount(key);
        info1.setUserName(key);
        userInfoMapper.insert(info1);
        userInfoMapper.insert(info1);
    }

}
