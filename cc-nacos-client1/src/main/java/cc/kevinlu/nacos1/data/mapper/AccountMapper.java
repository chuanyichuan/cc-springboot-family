package cc.kevinlu.nacos1.data.mapper;

import cc.kevinlu.nacos1.data.model.AccountDO;
import cc.kevinlu.nacos1.data.model.AccountDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
* @author lucunyu
* @time 2021-04-19
*/
public interface AccountMapper {
    long countByExample(AccountDOExample example);

    int deleteByExample(AccountDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AccountDO record);

    int insertSelective(AccountDO record);

    List<AccountDO> selectByExample(AccountDOExample example);

    AccountDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AccountDO record, @Param("example") AccountDOExample example);

    int updateByExample(@Param("record") AccountDO record, @Param("example") AccountDOExample example);

    int updateByPrimaryKeySelective(AccountDO record);

    int updateByPrimaryKey(AccountDO record);
}