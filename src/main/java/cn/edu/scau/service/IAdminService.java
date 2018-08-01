package cn.edu.scau.service;

import cn.edu.scau.entity.Admin;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface IAdminService {

    /**
     * 登录
     * @param admin
     * @return
     */
    public Map<String, Object> login(HttpServletRequest request, Admin admin);

    /**
     * 注销
     * @param request
     * @return
     */
    public Map<String, Object> logout(HttpServletRequest request);

}
