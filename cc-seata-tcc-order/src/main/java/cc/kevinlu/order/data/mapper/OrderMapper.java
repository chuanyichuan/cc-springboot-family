package cc.kevinlu.order.data.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cc.kevinlu.order.data.model.OrderDO;
import cc.kevinlu.order.data.model.OrderDOExample;

/**
* @author lucunyu
* @time 2021-04-19
*/
public interface OrderMapper {
    long countByExample(OrderDOExample example);

    int deleteByExample(OrderDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OrderDO record);

    int insertSelective(OrderDO record);

    List<OrderDO> selectByExample(OrderDOExample example);

    OrderDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OrderDO record, @Param("example") OrderDOExample example);

    int updateByExample(@Param("record") OrderDO record, @Param("example") OrderDOExample example);

    int updateByPrimaryKeySelective(OrderDO record);

    int updateByPrimaryKey(OrderDO record);
}
