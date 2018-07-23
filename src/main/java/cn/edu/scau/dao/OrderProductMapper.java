package cn.edu.scau.dao;

import cn.edu.scau.entity.OrderProduct;

public interface OrderProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderProduct record);

    int insertSelective(OrderProduct record);

    OrderProduct selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderProduct record);

    int updateByPrimaryKey(OrderProduct record);
}