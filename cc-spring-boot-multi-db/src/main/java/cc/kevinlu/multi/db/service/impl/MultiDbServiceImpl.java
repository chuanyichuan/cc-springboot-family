package cc.kevinlu.multi.db.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cc.kevinlu.multi.db.annotation.MultiTransaction;
import cc.kevinlu.multi.db.dao.mapper.bugfree.BugsMapper;
import cc.kevinlu.multi.db.dao.mapper.codeac.UserMapper;
import cc.kevinlu.multi.db.dao.model.source1.BugsDO;
import cc.kevinlu.multi.db.dao.model.source2.User;
import cc.kevinlu.multi.db.service.MultiDbService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MultiDbServiceImpl implements MultiDbService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private BugsMapper bugsMapper;

    @Override
    public void saveMultiNoTransaction() {
        BugsDO bugsDO = new BugsDO();
        bugsDO.setBugId("1123");
        bugsDO.setContent("fsdd");
        bugsDO.setStatus(1);
        bugsDO.setUserId("123efdsd");
        bugsDO.setIsDeleted(1);
        bugsDO.setTitle("ihgshdjakl");
        bugsMapper.insertSelective(bugsDO);

        User user = new User();
        user.setName("1adf1");
        user.setDes("1asd1");
        user.setPassword("1sdftre1");
        user.setSex(1);
        user.setUserType(1);
        userMapper.insert(user);

        int[] a = new int[2];
        System.out.println(a[5]);
    }

    @Override
    @Transactional(value = "bugfree_transaction", rollbackFor = Exception.class)
    public void saveMultiWithOneTransaction() {
        User user = new User();
        user.setName("adf");
        user.setDes("asd");
        user.setPassword("sdftre");
        user.setSex(1);
        user.setUserType(1);
        userMapper.insert(user);

        BugsDO bugsDO = new BugsDO();
        bugsDO.setBugId("1123");
        bugsDO.setContent("fsdd");
        bugsDO.setStatus(1);
        bugsDO.setUserId("123efdsd");
        bugsDO.setIsDeleted(1);
        bugsDO.setTitle("ihgshdjakl");
        bugsMapper.insertSelective(bugsDO);

        int[] a = new int[2];
        System.out.println(a[5]);
    }

    @Override
    @Transactional(value = "codeac_transaction", rollbackFor = Exception.class)
    public void saveMultiWithTwoTransaction() {
        BugsDO bugsDO = new BugsDO();
        bugsDO.setBugId("1123");
        bugsDO.setContent("fsdd");
        bugsDO.setStatus(1);
        bugsDO.setUserId("123efdsd");
        bugsDO.setIsDeleted(1);
        bugsDO.setTitle("ihgshdjakl");
        bugsMapper.insertSelective(bugsDO);

        User user = new User();
        user.setName("3adf");
        user.setDes("3asd");
        user.setPassword("3sdftre");
        user.setSex(1);
        user.setUserType(1);
        userMapper.insert(user);

        int[] a = new int[2];
        System.out.println(a[5]);
    }

    @Override
    @MultiTransaction(values = { "bugfree_transaction", "codeac_transaction" })
    public void saveMultiWithMultiTransaction() {
        BugsDO bugsDO = new BugsDO();
        bugsDO.setBugId("51123");
        bugsDO.setContent("5fsdd");
        bugsDO.setStatus(1);
        bugsDO.setUserId("5123efdsd");
        bugsDO.setIsDeleted(1);
        bugsDO.setTitle("5i5hgshdjakl");
        bugsMapper.insertSelective(bugsDO);

        User user = new User();
        user.setName("4adf");
        user.setDes("5asd");
        user.setPassword("6sdftre");
        user.setSex(1);
        user.setUserType(1);
        userMapper.insert(user);

        int[] a = new int[2];
        System.out.println(a[5]);
    }
}
