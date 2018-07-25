package cn.edu.scau.controller;

import cn.edu.scau.entity.Log;
import cn.edu.scau.component.Page;
import cn.edu.scau.service.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/log")
public class LogController {

    @Autowired
    private ILogService logService;

    /**
     * 删除单条日志
     * @param request
     * @return
     */
    @RequestMapping("/deleteOne")
    @ResponseBody
    public Map<String, Object> deleteOne(@RequestBody Map<String, Integer> request) {
        return logService.deleteOne(request);
    }

    /**
     * 批量删除日志
     * @param ids
     * @return
     */
    @RequestMapping("/deleteMore")
    @ResponseBody
    public Map<String, Object> deleteMore(@RequestBody Integer[] ids) {
        return logService.deleteMore(ids);
    }

    /**
     * 搜索日志
     * @param request
     * @return
     */
    @RequestMapping("/search")
    @ResponseBody
    public Page<Log> search(@RequestBody Page<Log> request) {
        return logService.serach(request);
    }

}
