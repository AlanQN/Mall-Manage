package cn.edu.scau.dao;

import cn.edu.scau.entity.OrderProduct;

public interface OrderProductMapper {

    int delete(Integer id);

    int insert(OrderProduct record);

    OrderProduct select(Integer id);

    int update(OrderProduct record);
}