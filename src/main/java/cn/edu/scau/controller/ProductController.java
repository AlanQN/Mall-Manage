package cn.edu.scau.controller;

import cn.edu.scau.component.Info;
import cn.edu.scau.entity.Product;
import cn.edu.scau.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.math.BigDecimal;
import java.util.Map;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductServiceImpl productService;

    @RequestMapping("/read_id")
    @ResponseBody
    public Info<Product> read_id(@RequestBody Map<String ,Integer> request){
        return  productService.selectByPrimaryKey(request);
    }

    @RequestMapping("/read_price")
    @ResponseBody
    public Info<Product> read_price(@RequestBody Map<String ,BigDecimal> request){

        return  productService.selectByPrice(request);
    }

    @RequestMapping("/modify")
    @ResponseBody
    public Info<Product> modify(@RequestBody Product record){
        return productService.updateByPrimaryKeySelective(record);
    }

    @RequestMapping("/add")
    @ResponseBody
    public Info<Product> add(@RequestBody Product record){
        return productService.insert(record);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Info<Product> delete(@RequestBody Map<String ,Integer> request){
        return productService.deleteByPrimaryKey(request);
    }
}

