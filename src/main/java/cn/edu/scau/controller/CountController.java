package cn.edu.scau.controller;

import cn.edu.scau.annotation.SystemControllerLog;
import cn.edu.scau.dto.Result;
import cn.edu.scau.entity.Count;
import cn.edu.scau.service.impl.CountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/count")
public class CountController {

    @Autowired
    CountServiceImpl countService;

    @SystemControllerLog(description = "获取统计信息")
    @RequestMapping("/data")
    @ResponseBody
    public Result<List<Count>> insert(@RequestBody Map<String, Object> map) {
        String start = (String) map.get("start");
        String end = (String) map.get("end");
        return countService.getDate(start,end);
    }

}
