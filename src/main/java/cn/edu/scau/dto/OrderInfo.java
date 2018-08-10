package cn.edu.scau.dto;

import cn.edu.scau.entity.Order;
import cn.edu.scau.entity.OrderProduct;
import cn.edu.scau.entity.OrderShipping;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderInfo {

    private OrderShipping orderShipping;

    private List<OrderProduct> orderProductList;

    private Order order;

    public OrderInfo(){}

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public OrderShipping getOrderShipping() {
        return orderShipping;
    }

    public void setOrderShipping(OrderShipping orderShipping) {
        this.orderShipping = orderShipping;
    }

    public List<OrderProduct> getOrderProductList() {
        return orderProductList;
    }

    public void setOrderProductList(List<OrderProduct> orderProductList) {
        this.orderProductList = orderProductList;
    }
}
