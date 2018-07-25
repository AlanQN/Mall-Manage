package cn.edu.scau.service.impl;

import cn.edu.scau.component.Page;
import cn.edu.scau.dao.OrderMapper;
import cn.edu.scau.dao.OrderProductMapper;
import cn.edu.scau.dao.OrderShippingMapper;
import cn.edu.scau.entity.Order;
import cn.edu.scau.entity.OrderShipping;
import cn.edu.scau.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderShippingMapper orderShippingMapper;
    @Autowired
    private OrderProductMapper orderProductMapper;

    /**
     * 获取全部订单
     *
     * @return
     */
    @Override
    public Map<String, Object> getAll() {
        Map<String, Object> map = new HashMap<>();
        Integer errorCode = 0;
        List<Order> orders;
        if ((orders = orderMapper.list()) == null) {
            errorCode = 1;
        }
        map.put("errorCode", errorCode);
        map.put("orderList", orders);
        return map;
    }

    /**
     * 添加订单
     * 订单状态初始为1 未付款
     *
     * @param order
     * @return
     */
    @Override
    public Map<String, Object> save(Order order) {
        Map<String, Object> map = new HashMap<>();
        Integer errorCode = 0;
        order.setStatus(1);
        order.setCreateTime(new Date());
        order.setUpdateTime(new Date());
        if (orderMapper.save(order) == 0) {
            errorCode = 1;
        }
        map.put("errorCode", errorCode);
        return map;
    }

    /**
     * 订单发货
     * 修改订单更新时间和订单状态
     * 添加订单快递信息
     *
     * @param orderShipping
     * @return 1表示获取订单失败，2表示更新订单失败，3表示发货失败,4订单已发货
     */
    @Override
    public Map<String, Object> consign(OrderShipping orderShipping) {
        Map<String, Object> map = new HashMap<>();
        Integer errorCode = 0;
        Order order;
        if ((order = orderMapper.getById(orderShipping.getOrderId())) == null) {
            errorCode = 1;
        } else if (order.getStatus() == 2 && (orderShippingMapper.select(orderShipping.getOrderId()) == null)) {
            //订单状态为未发货且订单的物流信息不存在
            order.setUpdateTime(new Date());
            order.setStatus(3);
            if (orderMapper.update(order) == 0) {
                errorCode = 2;
            }
            orderShipping.setCreated(new Date());
            if (orderShippingMapper.insert(orderShipping) == 0) {
                errorCode = 3;
            }
        } else {
            errorCode = 4;
        }
        map.put("errorCode", errorCode);
        return map;
    }

    /**
     * 获取订单详情
     * @param id
     * @return
     */
    @Override
    public Map<String, Object> info(Integer id) {
        Map<String, Object> map = new HashMap<>();
        Integer errorCode = 0;

        map.put("errorCode", errorCode);
        return map;
    }

    /**
     * 订单取消
     * @param id
     * @return
     */
    @Override
    public Map<String, Object> cancel(Integer id) {
        Map<String, Object> map = new HashMap<>();
        Integer errorCode = 0;
        Order order = orderMapper.getById(id);
        if(order.getStatus() == 1 || order.getStatus() ==2)
        {
            order.setStatus(6);
            orderMapper.update(order);
        }else {
            errorCode = 1;
        }
        map.put("errorCode", errorCode);
        return map;
    }

    /**
     * 新的备注
     * @param order
     * @return
     */
    @Override
    public Map<String, Object> description(Order order) {
        Map<String, Object> map = new HashMap<>();
        Integer errorCode = 0;
        orderMapper.update(order);
        map.put("errorCode", errorCode);
        return map;
    }

    @Override
    public Map<String, Object> delete(Integer id) {
        Map<String, Object> map = new HashMap<>();
        Integer errorCode = 0;
        Order order = orderMapper.getById(id);
        if(order.getStatus() == 6 )
        {
            orderMapper.delete(id);
        }else {
            errorCode = 1;
        }
        map.put("errorCode", errorCode);
        return map;
    }

    @Override
    public Map<String, Object> search(String string) {
        Map<String, Object> map = new HashMap<>();
        Integer errorCode = 0;
        String s1 = "%"+string+"%";
        List<Order> orderList = orderMapper.search(s1);
        if (orderList != null){
            map.put("errorCode" ,errorCode);
            map.put("orderList",orderList);
        }else {
            errorCode = 1;
            map.put("errorCode" ,errorCode);
        }
        return map;
    }

    @Override
    public Map<String, Object> deleteMore(Integer[] ids) {
        Map<String,Object> map = new HashMap<>();
        //删除批量选定的订单
        if(orderMapper.deleteMore(ids) > 0){
            map.put("errorCode",0);
        }else {
            map.put("errorCode",1);
        }
        return map;
    }

    @Override
    public Page<Order> getPage(Page<Order> page) {
        //查询记录总数
        int total = orderMapper.getTotal(page);
        page.setTotalRecord(total);
        page.setTotalPage((int)Math.ceil((double)total/page.getPageSize()));
        page.setStartIndex((page.getPageNum()-1)*page.getPageSize());
        //查找记录
        if (page.getPageNum() > 0 && page.getPageNum() <= page.getTotalPage()) {
            List<Order> orders = orderMapper.findRecords(page);
            page.setRecords(orders);
            page.setRecordNum(orders.size());
        }
        return page;
    }

}
