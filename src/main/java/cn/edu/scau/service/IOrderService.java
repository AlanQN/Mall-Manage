package cn.edu.scau.service;

import cn.edu.scau.component.Page;
import cn.edu.scau.dto.OrderInfo;
import cn.edu.scau.dto.Result;
import cn.edu.scau.entity.Order;
import cn.edu.scau.entity.OrderShipping;

import java.util.List;
import java.util.Map;

public interface IOrderService {

    /**
     * 通过id获取订单
     *
     * @param id 订单id
     * @return
     */
    Result<Order> getById(Integer id);

    /**
     * 获取订单详情
     *
     * @param offset 起始位置
     * @param limit  数量
     * @param id     订单id
     * @return
     */
    Result<OrderInfo> getOrderInfoById(Integer offset, Integer limit, Integer id);

    /**
     * 批量删除订单
     * @param ids id数组
     * @return
     */
    Result<String> delete(Integer[] ids);

    /**
     * 增加或修改备注
     * @param id 订单id
     * @param description 备注
     * @return
     */
    Result<String> description(Integer id, String description);

    /**
     * 取消订单
     * 只有未付款和未发货的订单能取消
     * @param id
     * @return
     */
    Result<String> cancel(Integer id);

    /**
     * 发货
     * @param orderId 订单id
     * @param shippingId 物流单号
     * @param shippingName 物流名称
     * @return
     */
    Result<String> consign(Integer orderId, String shippingId, String shippingName);

    /**
     * 分页获取订单
     * @param pageNum 页
     * @param pageSize 数量
     * @return
     */
    Result<Page<Order>> orderList(Integer pageNum, Integer pageSize);

    /**
     * 搜索
     * @param pageNum 页
     * @param pageSize 数量
     * @param key 关键字
     * @return
     */
    Result<Page<Order>> search(Integer pageNum, Integer pageSize, String key);

    /**
     * 添加订单
     * @param order
     * @return
     */
    Result<String> add(Order order);
}
