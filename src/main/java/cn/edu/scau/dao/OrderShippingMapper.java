package cn.edu.scau.dao;

import cn.edu.scau.entity.OrderShipping;

public interface OrderShippingMapper {

    /**
     * 删除
     * @param orderId
     * @return
     */
    int deleteById(Integer orderId);

    /**
     * 添加
     * @param record
     * @return
     */
    int insert(OrderShipping record);

    /**
     * 获取
     * @param orderId
     * @return
     */
    OrderShipping selectById(Integer orderId);

    /**
     * 更新
     * @param record
     * @return
     */
    int update(OrderShipping record);
}