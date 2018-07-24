package cn.edu.scau.service.impl;

import cn.edu.scau.dao.UserMapper;
import cn.edu.scau.entity.User;
import cn.edu.scau.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    public User find(Map<String, String> request) {
        //获取id
        Integer id = Integer.valueOf(request.get("id"));
        User user = userMapper.find(id);
        return user;
    }

    @Override
    public Map<String, Object> add(User user) {
        Map<String, Object> response = new HashMap<String, Object>();
        Integer errorCode = -1;
        //设置创建时间和更新时间
        user.setCreated(new Date());
        user.setUpdated(new Date());
        if (userMapper.findByName(user.getUsername()) != null) {    //判断用户名是否已存在
            errorCode = 1;
        } else if (userMapper.findByPhone(user.getPhone()) != null) {  //判断手机号是否被使用
            errorCode = 2;
        } else if (userMapper.findByEmail(user.getEmail()) != null) {  //判断邮箱是否被使用
            errorCode = 3;
        } else {
            userMapper.insert(user);
            errorCode = 0;
        }
        response.put("errorCode", errorCode);
        return response;
    }

}
