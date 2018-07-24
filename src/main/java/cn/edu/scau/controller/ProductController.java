package cn.edu.scau.controller;

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

    @RequestMapping("/read_id")
    @ResponseBody
    public Product read_id(@RequestBody Map<String ,Integer> request){
        return  productService.selectByPrimaryKey(request);
    }

    @RequestMapping("/read_price")
    @ResponseBody
    public List<Product> read_price(@RequestBody Map<String ,BigDecimal> request){

        return  productService.selectByPrice(request);
    }

}

