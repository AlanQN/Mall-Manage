package cn.edu.scau.dao;

import cn.edu.scau.entity.OrderProduct;

import java.util.List;

public interface OrderProductMapper {

    /**
     * 删除
     * @param id
     * @return
     */
    int delete(Integer id);

    /**
     * 添加
     * @param record
     * @return
     */
    int insert(OrderProduct record);

    /**
     * 通过id获取
     * @param id
     * @return
     */
    OrderProduct selectById(Integer id);

    /**
     * 更新通过id
     * @param record
     * @return
     */
    int updateById(OrderProduct record);

    /**
     * 获取order_id的所有商品
     * @param orderId
     * @return
     */
    List<OrderProduct> selectByOrderId(Integer orderId);


}