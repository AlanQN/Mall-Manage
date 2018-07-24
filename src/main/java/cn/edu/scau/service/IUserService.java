package cn.edu.scau.service;

import cn.edu.scau.entity.User;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.Map;

public interface IUserService {

    /**
     * 编辑用户时获取此用户的信息
     * @param request 请求参数
     * @return
     */
    public User find(Map<String, String> request);


    /**
     * 添加用户
     * @param user
     * @return
     */
    public Map<String, Object> add(User user);

}
