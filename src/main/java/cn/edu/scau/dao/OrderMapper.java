package cn.edu.scau.dao;

import cn.edu.scau.entity.Order;

import java.util.List;

public interface OrderMapper {


    /**
     * 删除订单
     * @param orderId
     * @return
     */
    int deleteById(Integer orderId);

    /**
     * 批量删除订单
     * @param ordersId
     * @return
     */
    int deleteMore(List<Integer> ordersId);

    /**
     * 添加订单
     * @param record
     * @return
     */
    int insert(Order record);

    /**
     * 获取全部订单
     * @return
     */
    List<Order> lists();

    /**
     * 通过id获取订单
     * @param orderId
     * @return
     */
    Order selectById(Integer orderId);

    /**
     * 部分更新
     * @param record
     * @return
     */
    int updateByIdSelective(Order record);

    /**
     * 更新
     * @param record
     * @return
     */
    int updateById(Order record);

    /**
     * 搜索
     * @param text
     * @return
     */
    List<Order> search(String text);
}