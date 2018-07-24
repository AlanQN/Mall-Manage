package cn.edu.scau.controller;

import cn.edu.scau.entity.User;
import cn.edu.scau.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @RequestMapping("/edit")
    @ResponseBody
    public User edit(@RequestBody Map<String, String> request) {
        return userService.find(request);
    }

    @RequestMapping("/add")
    @ResponseBody
    public Map<String, Object> add(@RequestBody User user) {
        return userService.add(user);
    }

}
