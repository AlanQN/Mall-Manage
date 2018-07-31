package cn.edu.scau.service.impl;

import cn.edu.scau.dao.AdminMapper;
import cn.edu.scau.entity.Admin;
import cn.edu.scau.entity.User;
import cn.edu.scau.interceptor.LoginInterceptor;
import cn.edu.scau.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Service
public class AdminServiceImpl implements IAdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Map<String, Object> login(HttpServletRequest request, Admin admin) {
        Map<String, Object> response = new HashMap<String, Object>();
        //查找用户
        Admin myAdmin = adminMapper.findByField(admin);
        //用户不存在，登录失败
        if (myAdmin == null) {
            response.put("result", false);
            return response;
        }
        //登录成功，把用户信息保存在session中
        HttpSession session = request.getSession();
        session.setAttribute(LoginInterceptor.USER_INFO_KEY, myAdmin);
        //封装结果
        response.put("result", true);
        response.put("user", myAdmin);
        return response;
    }

    @Override
    public Map<String, Object> logout(HttpServletRequest request) {
        Map<String, Object> response = new HashMap<String, Object>();
        try {
            //获取session
            HttpSession session = request.getSession();
            Admin admin = (Admin) session.getAttribute(LoginInterceptor.USER_INFO_KEY);
            System.out.println(admin.getName());
            //删除登录用户信息
            session.removeAttribute(LoginInterceptor.USER_INFO_KEY);
            //设置session失效
            session.invalidate();
        } catch (Exception e) {
            e.printStackTrace();
            response.put("result", false);
            return response;
        }
        response.put("result", true);
        return response;
    }

}
