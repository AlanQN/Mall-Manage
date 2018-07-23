package cn.edu.scau.controller;

import cn.edu.scau.entity.User;
import cn.edu.scau.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource(name = "userService")
    UserServiceImpl userService;

    @RequestMapping("/edit")
    public String edit(Model model, Integer id) {
        User user = userService.find(id);
        model.addAttribute("user", user);
        return "edit";
    }

}
