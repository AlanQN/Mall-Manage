package cn.edu.scau.controller;

import cn.edu.scau.component.Page;
import cn.edu.scau.dto.Result;
import cn.edu.scau.entity.Express;
import cn.edu.scau.service.IExpressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Map;

@Controller
@RequestMapping("/express")
public class ExpressController {

    @Autowired
    private IExpressService expressService;


    @RequestMapping("/add")
    @ResponseBody
    public Result<String> insert(@RequestBody Map<String, Object> map) {
        String name = (String) map.get("name");
        return expressService.insert(name);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Result<String> delete(@RequestBody Map<String, Object> map) {
        ArrayList<Integer> i = (ArrayList<Integer>) map.get("ids");
        Integer[] ids = i.toArray(new Integer[i.size()]);
        return expressService.delete(ids);
    }

    @RequestMapping("/edit")
    @ResponseBody
    public Result<Express> get(@RequestBody Map<String, Integer> map) {
        return expressService.get(map.get("id"));
    }

    @RequestMapping("/modify")
    @ResponseBody
    public Result<String> update(@RequestBody Map<String, Express> map) {
        return expressService.update(map.get("express"));
    }

    @RequestMapping("/search")
    @ResponseBody
    public Result<Page<Express>> search(@RequestBody Map<String, Object> map) {
        Integer pageNum = (Integer) map.get("pageNum");
        Integer pageSize = (Integer)map.get("pageSize");
        String key = (String) map.get("key");
        return expressService.search(pageNum,pageSize,key);
    }

    @RequestMapping("/getlist")
    @ResponseBody
    public Result<Page<Express>> getPage(@RequestBody Map<String, Object> map) {
        Integer pageNum = (Integer) map.get("pageNum");
        Integer pageSize = (Integer)map.get("pageSize");
        return expressService.getPage(pageNum,pageSize);
    }


}
