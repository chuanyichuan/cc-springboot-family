package cc.kevinlu.multi.db.dao.mapper.bugfree;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cc.kevinlu.multi.db.dao.model.source1.BugsDO;
import cc.kevinlu.multi.db.dao.model.source1.BugsDOExample;

/**
* bf_bugs
* 
* @author chuanchuan
* 
* @time 2020-10-13
*/
public interface BugsMapper {
    long countByExample(BugsDOExample example);

    int deleteByExample(BugsDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BugsDO record);

    int insertSelective(BugsDO record);

    List<BugsDO> selectByExampleWithBLOBs(BugsDOExample example);

    List<BugsDO> selectByExample(BugsDOExample example);

    BugsDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BugsDO record, @Param("example") BugsDOExample example);

    int updateByExampleWithBLOBs(@Param("record") BugsDO record, @Param("example") BugsDOExample example);

    int updateByExample(@Param("record") BugsDO record, @Param("example") BugsDOExample example);

    int updateByPrimaryKeySelective(BugsDO record);

    int updateByPrimaryKeyWithBLOBs(BugsDO record);

    int updateByPrimaryKey(BugsDO record);
}
