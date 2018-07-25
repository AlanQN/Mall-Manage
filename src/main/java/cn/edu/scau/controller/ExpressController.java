package cn.edu.scau.controller;

import cn.edu.scau.entity.Express;
import cn.edu.scau.service.IExpressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/express")
public class ExpressController {

    @Autowired
    private IExpressService expressService;

    @RequestMapping("/add")
    @ResponseBody
    public Map<String,Object> insert(@RequestBody Map<String,Object> map){
        return expressService.insert((String) map.get("name"));
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Map<String,Object> delete(@RequestBody Map<String,Object> map){
        return expressService.delete(Integer.valueOf((String) map.get("id")));
    }

    @RequestMapping("/edit")
    @ResponseBody
    public Map<String,Object> get(@RequestBody Map<String,Integer> map){
        return expressService.get( map.get("id"));
    }

    @RequestMapping("/modify")
    @ResponseBody
    public Map<String,Object> update(@RequestBody Map<String,Express> map){
        return expressService.update( map.get("express"));
    }

    @RequestMapping("/search")
    @ResponseBody
    public Map<String,Object> search(@RequestBody Map<String,Object> map){
        return expressService.search((String) map.get("string"));
    }

    @RequestMapping("/getAll")
    @ResponseBody
    public Map<String,Object> getAll(){
        return expressService.getAll();
    }

}
