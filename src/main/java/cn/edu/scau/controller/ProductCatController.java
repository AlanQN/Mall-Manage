package cn.edu.scau.controller;

import cn.edu.scau.annotation.SystemControllerLog;
import cn.edu.scau.component.Info;
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

    @SystemControllerLog(description = "商品分类查找通过id")
    @RequestMapping("/read_id")
    @ResponseBody
    public Info<ProductCat> read_id(@RequestBody Map<String ,Integer> request){
        return  productCatService.selectByPrimaryKey(request);
    }

    @SystemControllerLog(description = "商品分类查找通过price")
    @RequestMapping("/delete")
    @ResponseBody
    public Info<ProductCat> delete(@RequestBody Map<String ,Integer> request){
        return productCatService.deleteByPrimaryKey(request);
    }

    @SystemControllerLog(description = "商品分类添加")
    @RequestMapping("/add")
    @ResponseBody
    public Info<ProductCat> add(@RequestBody ProductCat record){
        return productCatService.insert(record);
    }

    @SystemControllerLog(description = "商品分类信息修改")
    @RequestMapping("/modify")
    @ResponseBody
    public Info<ProductCat> modify(@RequestBody ProductCat record){
        return productCatService.updateByPrimaryKeySelective(record);
    }
}
