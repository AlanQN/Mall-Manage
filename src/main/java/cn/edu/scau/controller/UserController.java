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
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    /**
     * 添加用户
     * @param user
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public Map<String, Object> add(@RequestBody User user) {
        return userService.add(user);
    }

    /**
     * 移除单个用户
     * @param request
     * @return
     */
    @RequestMapping("/removeOne")
    @ResponseBody
    public Map<String, Object> removeOne(@RequestBody Map<String, Integer> request) {
        return userService.removeOne(request);
    }

    /**
     * 批量移除用户
     * @param request
     * @return
     */
    @RequestMapping("/removeMore")
    @ResponseBody
    public Map<String, Object> removeMore(@RequestBody Integer[] request) {
        return userService.removeMore(request);
    }

    /**
     * 停用用户
     * @param request
     * @return
     */
    @RequestMapping("/pause")
    @ResponseBody
    public Map<String, Object> pause(@RequestBody Map<String, Integer> request) {
        return userService.pause(request);
    }

    /**
     * 启用用户
     * @param request
     * @return
     */
    @RequestMapping("/resume")
    @ResponseBody
    public Map<String, Object> resume(@RequestBody Map<String, Integer> request) {
        return userService.resume(request);
    }

    /**
     * 编辑用户
     * @param request
     * @return
     */
    @RequestMapping("/edit")
    @ResponseBody
    public User edit(@RequestBody Map<String, Integer> request) {
        return userService.find(request);
    }

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    @RequestMapping("/modify")
    @ResponseBody
    public Map<String, Object> modify(@RequestBody User user) {
        return userService.modify(user);
    }

    /**
     * 修改密码
     * @param user
     * @return
     */
    @RequestMapping("/changepwd")
    @ResponseBody
    public Map<String, Object> changepwd(@RequestBody User user) {
        return userService.changepwd(user);
    }

    /**
     * 删除单个用户
     * @param request
     * @return
     */
    @RequestMapping("/deleteOne")
    @ResponseBody
    public Map<String, Object> deleteOne(@RequestBody Map<String, Integer> request) {
        return userService.deleteOne(request);
    }

    /**
     * 批量删除用户
     * @param request
     * @return
     */
    @RequestMapping("/deleteMore")
    @ResponseBody
    public Map<String, Object> deleteMore(@RequestBody Integer[] request) {
        return userService.deleteMore(request);
    }

}