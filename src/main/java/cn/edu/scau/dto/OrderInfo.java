package cn.edu.scau.dto;

import cn.edu.scau.entity.OrderProduct;
import cn.edu.scau.entity.OrderShipping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderInfo {

    private OrderShipping orderShipping;

    private List<OrderProduct> orderProductList;

    public OrderInfo(){}

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
