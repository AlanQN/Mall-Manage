package cn.edu.scau.controller;

import cn.edu.scau.annotation.SystemControllerLog;
import cn.edu.scau.service.impl.ProServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.edu.scau.entity.Pro;
import java.util.List;
import java.util.Map;
import cn.edu.scau.component.Info;

@Controller
@RequestMapping("/pro")
public class ProController {

    @Autowired
    ProServiceImpl proService;

    @SystemControllerLog(description = "新建的产品分类")
    @RequestMapping("/read")
    @ResponseBody
    public Info<List<Pro>> read(@RequestBody Map<String ,Integer> request){
        return proService.selectByTag(request);
    }

    @SystemControllerLog(description = "商品添加")
    @RequestMapping("/add")
    @ResponseBody
    public Info<Pro> insertBySome(@RequestBody Pro record){
        return proService.insertBySome(record);
    }
}
