package cn.edu.scau.dao;

import cn.edu.scau.entity.OrderProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderProductMapper {

    /**
     * 添加商品
     * @param record
     * @return
     */
    int insert(OrderProduct record);


    /**
     * 获取订单的全部商品
     * @param offset 起始位置
     * @param limit 数量
     * @param id 订单id
     * @return
     */
    List<OrderProduct> queryAllByID(@Param("offset") Integer offset,@Param("limit") Integer limit,@Param("id") Integer id);


    /**
     * 获取订单商品的总金额
     * @param id 订单id
     * @return
     */
    double getTotal(Integer id);
}