package cn.edu.scau.service.impl;

import cn.edu.scau.dao.UserMapper;
import cn.edu.scau.entity.User;
import cn.edu.scau.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl implements IUserService {

    @Resource(name = "userMapper")
    private UserMapper userMapper;

    @Override
    public User find(Integer id) {
        User user = userMapper.find(id);
        return user;
    }
}
