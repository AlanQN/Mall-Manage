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

    @Override
    public User find(Map<String, Integer> request) {
        //获取id
        Integer id = request.get("id");
        User user = userMapper.findById(id);
        return user;
    }

    @Override
    public Map<String, Object> add(User user) {
        Map<String, Object> response = new HashMap<String, Object>();
        Integer errorCode = -1;
        //设置创建时间和更新时间
        user.setCreated(new Date());
        user.setUpdated(new Date());
        if (userMapper.findByUniqueField(user) != null) {    //判断唯一字段是否发生重复
            errorCode = 1;
        } else {
            userMapper.insert(user);
            errorCode = 0;
        }
        response.put("errorCode", errorCode);
        return response;
    }

    @Override
    public Map<String, Object> removeOne(Map<String, Integer> request) {
        Map<String, Object> response = new HashMap<String, Object>();
        //获取要移除用户的id
        Integer id = request.get("id");
        if (userMapper.removeOne(id) > 0) {
            response.put("result", true);
        } else {
            response.put("result", false);
        }
        return response;
    }

    @Override
    public Map<String, Object> removeMore(Integer[] ids) {
        Map<String, Object> response = new HashMap<String, Object>();
        //移除指定的用户
        if (userMapper.removeMore(ids) > 0) {
            response.put("result", true);
        } else {
            response.put("result", false);
        }
        return response;
    }

    @Override
    public Map<String, Object> pause(Map<String, Integer> request) {
        Map<String, Object> response = new HashMap<String, Object>();
        //获取要禁用用户的id
        Integer id = request.get("id");
        //禁用用户
        if (userMapper.pause(id) > 0) {
            response.put("result", true);
        } else {
            response.put("result", false);
        }
        return response;
    }

    @Override
    public Map<String, Object> resume(Map<String, Integer> request) {
        Map<String, Object> response = new HashMap<String, Object>();
        //获取要启用用户的id
        Integer id = request.get("id");
        //启用用户
        if (userMapper.resume(id) > 0) {
            response.put("result", true);
        } else {
            response.put("result", false);
        }
        return response;
    }

    @Override
    public Map<String, Object> modify(User user) {
        Map<String, Object> response = new HashMap<String, Object>();
        //修改用户信息
        if (userMapper.findByUniqueField(user) == null && userMapper.update(user) > 0) {
            response.put("errorCode", 0);
        } else {
            response.put("errorCode", 1);
        }
        return response;
    }

    @Override
    public Map<String, Object> changepwd(User user) {
        Map<String, Object> response = new HashMap<String, Object>();
        //修改用户密码
        if (userMapper.update(user) > 0) {
            response.put("result", true);
        } else {
            response.put("result", false);
        }
        return response;
    }

    @Override
    public Map<String, Object> deleteOne(Map<String, Integer> request) {
        Map<String, Object> response = new HashMap<String, Object>();
        //获取要删除的用户编号
        Integer id = request.get("id");
        //删除用户
        if (userMapper.deleteOne(id) > 0) {
            response.put("result", true);
        } else {
            response.put("result", false);
        }
        return response;
    }

    @Override
    public Map<String, Object> deleteMore(Integer[] ids) {
        Map<String, Object> response = new HashMap<String, Object>();
        //删除批量选定的用户
        if (userMapper.deleteMore(ids) > 0) {
            response.put("result", true);
        } else {
            response.put("result", false);
        }
        return response;
    }


}
