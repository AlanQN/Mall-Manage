package cn.edu.scau.dao;

import cn.edu.scau.entity.OrderShipping;

public interface OrderShippingMapper {
    int deleteByPrimaryKey(Integer orderId);

    int insert(OrderShipping record);

    int insertSelective(OrderShipping record);

    OrderShipping selectByPrimaryKey(Integer orderId);

    int updateByPrimaryKeySelective(OrderShipping record);

    int updateByPrimaryKey(OrderShipping record);
}