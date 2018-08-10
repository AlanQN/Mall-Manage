package cn.edu.scau.controller;

import cn.edu.scau.annotation.SystemControllerLog;
import cn.edu.scau.component.Page;
import cn.edu.scau.dto.Result;
import cn.edu.scau.entity.Express;
import cn.edu.scau.service.IExpressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/express")
public class ExpressController {

    @Autowired
    private IExpressService expressService;

    @SystemControllerLog(description = "添加快递")
    @RequestMapping("/add")
    @ResponseBody
    public Result<String> insert(@RequestBody Map<String, Object> map) {
        String name = (String) map.get("name");
        return expressService.insert(name);
    }

    @SystemControllerLog(description = "删除快递")
    @RequestMapping("/delete")
    @ResponseBody
    public Result<String> delete(@RequestBody Map<String, Object> map) {
        ArrayList<Integer> i = (ArrayList<Integer>) map.get("ids");
        Integer[] ids = i.toArray(new Integer[i.size()]);
        return expressService.delete(ids);
    }

    @SystemControllerLog(description = "编辑快递")
    @RequestMapping("/edit")
    @ResponseBody
    public Result<Express> get(@RequestBody Map<String, Integer> map) {
        return expressService.get(map.get("id"));
    }

    @SystemControllerLog(description = "修改快递")
    @RequestMapping("/modify")
    @ResponseBody
    public Result<String> update(@RequestBody Map<String, Express> map) {
        return expressService.update(map.get("express"));
    }

    @SystemControllerLog(description = "搜索快递")
    @RequestMapping("/search")
    @ResponseBody
    public Result<Page<Express>> search(@RequestBody Map<String, Object> map) {
        Integer pageNum = (Integer) map.get("pageNum");
        Integer pageSize = (Integer)map.get("pageSize");
        String key = (String) map.get("key");
        return expressService.search(pageNum,pageSize,key);
    }

    @SystemControllerLog(description = "获取快递")
    @RequestMapping("/getlist")
    @ResponseBody
    public Result<Page<Express>> getPage(@RequestBody Map<String, Object> map) {
        Integer pageNum = (Integer) map.get("pageNum");
        Integer pageSize = (Integer)map.get("pageSize");
        return expressService.getPage(pageNum,pageSize);
    }


    @SystemControllerLog(description = "获取快递")
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    public Result<List<Express>> getAll() {
        return expressService.getAll();
    }


}
