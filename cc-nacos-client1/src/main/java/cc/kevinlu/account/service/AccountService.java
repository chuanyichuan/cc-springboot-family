package cc.kevinlu.account.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import cc.kevinlu.account.data.mapper.AccountMapper;
import cc.kevinlu.account.data.model.AccountDO;
import cc.kevinlu.account.data.model.AccountDOExample;

@Service
public class AccountService {

    @Resource
    private AccountMapper accountMapper;

    @Transactional(rollbackFor = Exception.class)
    public boolean money(String userId, int m) {
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
}
