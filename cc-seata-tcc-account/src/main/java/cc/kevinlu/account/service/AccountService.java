package cc.kevinlu.account.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import cc.kevinlu.account.action.AccountTccAction;
import cc.kevinlu.account.data.mapper.AccountMapper;
import cc.kevinlu.account.data.model.AccountDO;
import cc.kevinlu.account.data.model.AccountDOExample;
import io.seata.spring.annotation.GlobalTransactional;

@Service
public class AccountService {

    @Resource
    private AccountMapper    accountMapper;

    @Resource
    private AccountTccAction accountTccAction;

    @Transactional(rollbackFor = Exception.class)
    public boolean decrMoney(String userId, int m) {
        AccountDOExample example = new AccountDOExample();
        example.createCriteria().andUserIdEqualTo(userId);
        List<AccountDO> userList = accountMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(userList)) {
            throw new RuntimeException("user not exist!");
        }
        AccountDO user = userList.get(0);
        if (m > user.getMoney()) {
            throw new RuntimeException("it's so poor!");
        }
        user.setMoney(user.getMoney() - m);
        int ct = accountMapper.updateByPrimaryKeySelective(user);

        return ct == 1;
    }

    @GlobalTransactional
    public boolean accountTcc(String userId, int m) {
        if (!accountTccAction.prepare(null, userId, m)) {
            throw new RuntimeException("expense account error!");
        }
        return true;
    }
}
