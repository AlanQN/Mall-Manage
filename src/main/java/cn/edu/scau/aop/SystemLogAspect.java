package cn.edu.scau.aop;

import cn.edu.scau.annotation.SystemControllerLog;
import cn.edu.scau.dao.LogMapper;
import cn.edu.scau.entity.Admin;
import cn.edu.scau.entity.Log;
import cn.edu.scau.interceptor.LoginInterceptor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Date;


@Aspect
@Component
public class SystemLogAspect {

    @Autowired
    private LogMapper logMapper;

    //本地异常日志记录对象
    private static final Logger logger = LoggerFactory.getLogger(SystemLogAspect.class);

    //Controller层切点
    @Pointcut("@annotation(cn.edu.scau.annotation.SystemControllerLog)")
    public void controllerAspect() {
    }

    @Pointcut("execution(* cn.edu.scau.controller.UserController.logout(..))")
    public void logout() {
    }

    @Before("logout()")
    public void addLogoutLog(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.
                getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        //读取session中的用户

        Admin admin = (Admin) session.getAttribute(LoginInterceptor.USER_INFO_KEY);
        //获取请求ip
        String ip = getRemortIP(request);
        try {
            Log log = new Log();
            if(admin == null)
            {
                log.setUser("用户未登录");
            }else {
                log.setUser(admin.getName());
            }
            log.setName("管理员注销");
            log.setType(0);
            log.setIp(ip);
            log.setCreateDate(new Date());
            //保存数据库
            logMapper.insert(log);
        } catch (Exception ex) {
            //记录本地异常日志
            logger.error("==异常通知异常==");
            logger.error("异常信息:{}", ex.getMessage());

        }
    }


    /**
     * 前置通知 用于拦截Controller层记录用户的操作
     *
     * @param joinPoint 切点
     */
    @After("controllerAspect()")
    public void doAfter(JoinPoint joinPoint) {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.
                getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        //读取session中的用户

        Admin admin = (Admin) session.getAttribute(LoginInterceptor.USER_INFO_KEY);
        //获取请求ip
        String ip = getRemortIP(request);
        try {
            //*========控制台输出=========*//
            System.out.println("=====通知开始=====");
            System.out.println("请求方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
            System.out.println("方法描述:" + getControllerMethodDescription(joinPoint));
            System.out.println("请求人:" + admin.getName());
            System.out.println("请求IP:" + ip);
            //*========数据库日志=========*//

            Log log = new Log();

           if(admin == null)
           {
                log.setUser("用户未登录");
            }else {
               log.setUser(admin.getName());
            }
            log.setName(getControllerMethodDescription(joinPoint));
            log.setType(0);
            log.setIp(ip);
            log.setCreateDate(new Date());
            //保存数据库
            logMapper.insert(log);
            System.out.println("=====通知结束=====");
        } catch (Exception ex) {
            //记录本地异常日志
            logger.error("==异常通知异常==");
            logger.error("异常信息:{}", ex.getMessage());

        }

    }


    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    private static String getControllerMethodDescription (JoinPoint joinPoint) throws Exception{
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description = method.getAnnotation(SystemControllerLog. class).description();
                    break;
                }
            }
        }
        return description;
    }




    public String getRemortIP(HttpServletRequest request) {
        if (request.getHeader("x-forwarded-for") == null) {
            return request.getRemoteAddr();
        }
        return request.getHeader("x-forwarded-for");

    }
}