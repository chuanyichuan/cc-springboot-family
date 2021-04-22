package cc.kevinlu.storage.data.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cc.kevinlu.storage.data.model.StorageDO;
import cc.kevinlu.storage.data.model.StorageDOExample;

/**
* @author lucunyu
* @time 2021-04-19
*/
public interface StorageMapper {
    long countByExample(StorageDOExample example);

    int deleteByExample(StorageDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(StorageDO record);

    int insertSelective(StorageDO record);

    List<StorageDO> selectByExample(StorageDOExample example);

    StorageDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") StorageDO record, @Param("example") StorageDOExample example);

    int updateByExample(@Param("record") StorageDO record, @Param("example") StorageDOExample example);

    int updateByPrimaryKeySelective(StorageDO record);

    int updateByPrimaryKey(StorageDO record);
}
