package cn.edu.scau.service;

import cn.edu.scau.component.Page;
import cn.edu.scau.entity.Order;
import cn.edu.scau.entity.OrderShipping;

import java.util.Map;

public interface IOrderService {

    //获取订单列表
    public Map<String, Object> getAll();

    //添加订单
    public Map<String, Object> save(Order order);

    //订单发货
    public Map<String, Object> consign(OrderShipping orderShipping);

    //订单详情
    public Map<String, Object> info(Integer id);

    //取消订单
    public Map<String, Object> cancel(Integer id);

    //备注
    public Map<String, Object> description(Order order);

    //删除单个订单
    public Map<String, Object> delete(Integer id);

    //搜索
    public Map<String, Object> search(String string);

    public Map<String,Object>deleteMore(Integer[] ids);

    public Page<Order> getPage(Page<Order> page);

}
