package cn.edu.scau.controller;

import cn.edu.scau.entity.Order;
import cn.edu.scau.entity.OrderShipping;
import cn.edu.scau.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @RequestMapping("/getAll")
    @ResponseBody
    public Map<String,Object> getAll(){
        return orderService.getAll();
    }


    @RequestMapping("/add")
    @ResponseBody
    public Map<String,Object> save(@RequestBody Order order ){
        return orderService.save(order);
    }


    @RequestMapping("/consign")
    @ResponseBody
    public Map<String,Object> consign(@RequestBody OrderShipping orderShipping){
        return orderService.consign(orderShipping);
    }


    @RequestMapping("/description")
    @ResponseBody
    public Map<String,Object> description(@RequestBody Order order){
        return orderService.description(order);
    }

    @RequestMapping("/cancel")
    @ResponseBody
    public Map<String,Object> cancel(@RequestBody Map<String,Integer> map){
        return orderService.cancel(map.get("orderId"));
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Map<String,Object> delete(@RequestBody Map<String,Integer> map){
        return orderService.delete(map.get("orderId"));
    }

    @RequestMapping("/search")
    @ResponseBody
    public Map<String,Object> search(@RequestBody Map<String,String> map){
        return orderService.search(map.get("string"));
    }

}
