package cn.edu.scau.dao;

import cn.edu.scau.entity.Order;

import java.util.List;

public interface OrderMapper {

    int save(Order order);

    int delete(Integer id);

    int deleteMore(Integer[] list);

    List<Order> list();

    Order getById(Integer id);

    int update(Order order);

    List<Order> search(String string);

}