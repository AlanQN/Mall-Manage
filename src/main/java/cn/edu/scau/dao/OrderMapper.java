package cn.edu.scau.dao;

import cn.edu.scau.component.Page;
import cn.edu.scau.entity.Count;
import cn.edu.scau.entity.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {

    /**
     * 添加订单
     * @param order
     * @return
     */
    int save(Order order);

    /**
     * 通过id数组批量删除订单
     * @param ids
     * @return
     */
    int delete(Integer[] ids);

    /**
     * 查询所有订单
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return
     */
    List<Order> queryAll(@Param("offset") Integer offset , @Param("limit") Integer limit);

    /**
     * 通过id查询
     * @param id
     * @return
     */
    Order getById(Integer id);

    /**
     * 备注
     * @param id 订单id
     * @param description 备注
     * @return
     */
    int description(@Param("id") Integer id, @Param("description") String description);

    /**
     * 取消订单
     * @param id
     * @return
     */
    int cancel(Integer id);

    /**
     * 发货
     * @param orderId
     * @param shippingId
     * @param shippingName
     * @return
     */
    int consign(@Param("orderId") Integer orderId, @Param("shippingId")String shippingId, @Param("shippingName") String shippingName);

    /**
     * 获取订单总数
     * @return
     * @param orderPage
     */
    Integer getTotal(Page<Order> orderPage);

    /**
     * 分页查找（可以用关键字）
     * @param page
     * @return
     */
    List<Order> findRecords(Page page);

    /**
     *
     * @param start
     * @param end
     * @return
     */
    List<Count> listCount(@Param("start") String start , @Param("end") String end);
}