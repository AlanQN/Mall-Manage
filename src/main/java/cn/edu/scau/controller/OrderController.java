package cn.edu.scau.controller;


import cn.edu.scau.annotation.SystemControllerLog;
import cn.edu.scau.component.Page;
import cn.edu.scau.dto.OrderInfo;
import cn.edu.scau.dto.Result;
import cn.edu.scau.entity.Order;
import cn.edu.scau.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @RequestMapping("/get")
    @ResponseBody
    public Result<Order> getById(@RequestBody Map<String,Object> map){
        Integer orderId = (Integer) map.get("orderId");
        return  orderService.getById(orderId);
    }

    @RequestMapping("/info")
    @ResponseBody
    public Result<OrderInfo> getInfo(@RequestBody Map<String,Object> map){
        Integer offest = (Integer) map.get("offset");
        Integer limit = (Integer)map.get("limit");
        Integer orderId = (Integer)map.get("orderId");
        return orderService.getOrderInfoById(offest,limit,orderId);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Result<String> delete(@RequestBody Map<String,Object> map){
        ArrayList<Integer> list = (ArrayList<Integer>) map.get("ids");
        Integer[] ids = list.toArray(new Integer[list.size()]);
        return orderService.delete(ids);
    }

    @RequestMapping("/description")
    @ResponseBody
    public Result<String> description(@RequestBody Map<String,Object> map){
        Integer id = (Integer) map.get("orderId");
        String description = (String) map.get("description");
        return orderService.description(id,description);
    }

    @RequestMapping("cancel")
    @ResponseBody
    public Result<String> cancel(@RequestBody Map<String,Object> map){
        Integer id = (Integer)map.get("orderId");
        return orderService.cancel(id);
    }

    @RequestMapping("/consign")
    @ResponseBody
    public Result<String> consign(@RequestBody Map<String,Object> map){
        Integer orderId = (Integer) map.get("orderId");
        String shippingId = (String) map.get("shippingId");
        String shippingName = (String)map.get("shippingName");
        return orderService.consign(orderId,shippingId,shippingName);
    }

    @SystemControllerLog(description = "获取订单列表")
    @RequestMapping("/list")
    @ResponseBody
    public Result<Page<Order>> list(@RequestBody Map<String,Object> map){
        Integer pageNum = (Integer) map.get("pageNum");
        Integer pageSize = (Integer)map.get("pageSize");
        return orderService.orderList(pageNum,pageSize);
    }

    @RequestMapping("/search")
    @ResponseBody
    public Result<Page<Order>> search(@RequestBody Map<String,Object> map){
        Integer pageNum = (Integer) map.get("pageNum");
        Integer pageSize = (Integer)map.get("pageSize");
        String key = (String) map.get("key");
        return orderService.search(pageNum,pageSize,key);
    }

    @RequestMapping("/add")
    @ResponseBody
    public Result<String> add(@RequestBody Map<String,Order> map){
        Order order = (Order) map.get("order");
        return orderService.add(order);
    }
}
