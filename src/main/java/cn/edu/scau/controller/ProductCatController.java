package cn.edu.scau.controller;

import cn.edu.scau.entity.ProductCat;
import cn.edu.scau.service.impl.ProductCatServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/productCat")
public class ProductCatController {

    @Autowired
    ProductCatServiceImpl productCatService;

    @RequestMapping("/read_id")
    @ResponseBody
    public ProductCat read_id(@RequestBody Map<String ,Integer> request){
        return  productCatService.selectByPrimaryKey(request);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Map<String,String> delete(@RequestBody Map<String ,Integer> request){
        return productCatService.deleteByPrimaryKey(request);
    }

    @RequestMapping("/add")
    @ResponseBody
    public Map<String,String> add(@RequestBody ProductCat record){
        return productCatService.insert(record);
    }

    @RequestMapping("/modify")
    @ResponseBody
    public Map<String,String> modify(@RequestBody ProductCat record){
        return productCatService.updateByPrimaryKeySelective(record);
    }
}
