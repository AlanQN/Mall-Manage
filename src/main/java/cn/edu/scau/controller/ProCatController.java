package cn.edu.scau.controller;

import cn.edu.scau.annotation.SystemControllerLog;
import cn.edu.scau.component.Info;
import cn.edu.scau.entity.ProCat;
import cn.edu.scau.service.impl.ProCatServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/procat")
public class ProCatController {

    @Autowired
    ProCatServiceImpl proCatService;

    @SystemControllerLog(description = "新建的产品分类")
    @RequestMapping("/read")
    @ResponseBody
    public Info<List<ProCat>> read(@RequestBody Map<String ,Integer> request){
        return proCatService.selectByTag(request);
    }

    @SystemControllerLog(description = "商品添加")
    @RequestMapping("/add")
    @ResponseBody
    public Info<ProCat> insertBySome(@RequestBody ProCat record){
        return proCatService.insertBySome(record);
    }
}
