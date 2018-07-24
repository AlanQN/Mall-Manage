package cn.edu.scau.dao;

import cn.edu.scau.entity.OrderShipping;

public interface OrderShippingMapper {

    int delete(Integer orderId);

    int insert(OrderShipping record);

    OrderShipping select(Integer orderId);

    int update(OrderShipping record);

}