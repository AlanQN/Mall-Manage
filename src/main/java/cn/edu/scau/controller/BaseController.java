package cn.edu.scau.controller;

import cn.edu.scau.entity.Base;
import cn.edu.scau.service.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/system")
public class BaseController {

    @Autowired
    private IBaseService baseService;

    /**
     * 获取基本设置信息
     * @return
     */
    @RequestMapping("/basic")
    @ResponseBody
    public Base basic() {
        return baseService.getBasicInfo();
    }

    @RequestMapping("/modify")
    @ResponseBody
    public Map<String, Object> modify(@RequestBody Base base) {
        return baseService.modifyBasicInfo(base);
    }

}
