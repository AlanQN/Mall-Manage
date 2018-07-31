package cn.edu.scau.interceptor;

import cn.edu.scau.entity.Admin;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class LoginInterceptor implements HandlerInterceptor {

    public static final String USER_INFO_KEY = "username";
    private List<String> excludeUrl;    //不拦截的url请求

    public List<String> getExcludeUrl() {
        return excludeUrl;
    }

    public void setExcludeUrl(List<String> excludeUrl) {
        this.excludeUrl = excludeUrl;
    }

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //获取Session对象
        HttpSession session = httpServletRequest.getSession();
        //获取请求的url地址
        String requestURL = httpServletRequest.getRequestURL().toString();
        //如果该url是不拦截的url，则不拦截
        if (excludeUrl != null && excludeUrl.contains(requestURL)) {
            return true;
        }
        //获取用户信息
        Admin admin = (Admin) session.getAttribute(LoginInterceptor.USER_INFO_KEY);
        //如果没有登录，则拦截，并且跳转到登录页面
        if (admin == null) {
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
