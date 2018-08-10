package cn.edu.scau.controller;

import cn.edu.scau.annotation.SystemControllerLog;
import cn.edu.scau.component.Info;
import cn.edu.scau.entity.Product;
import cn.edu.scau.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductServiceImpl productService;

    @SystemControllerLog(description = "商品查找通过关键字")
    @RequestMapping("/read_name")
    @ResponseBody
    public Info<List<Product>> read_name(@RequestBody Map<String ,String> request){
        return  productService.selectByName(request);
    }

    @SystemControllerLog(description = "商品查找通过价格")
    @RequestMapping("/read_price")
    @ResponseBody
    public Info<List<Product>> read_price(@RequestBody Map<String ,BigDecimal> request){

        return  productService.selectByPrice(request);
    }

    @SystemControllerLog(description = "商品信息修改")
    @RequestMapping("/modify")
    @ResponseBody
    public Info<Product> modify(@RequestBody Product record){
        return productService.updateByPrimaryKeySelective(record);
    }

    @SystemControllerLog(description = "商品添加")
    @RequestMapping("/add")
    @ResponseBody
    public Info<Product> add(@RequestBody Product record){
        return productService.insert(record);
    }

    @SystemControllerLog(description = "商品删除")
    @RequestMapping("/delete")
    @ResponseBody
    public Info<Product> delete(@RequestBody Map<String ,Integer> request){
        return productService.deleteByPrimaryKey(request);
    }
}

