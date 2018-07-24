package cn.edu.scau.service;

import cn.edu.scau.entity.User;

import java.util.Map;

public interface IUserService {

    /**
     * 编辑用户时获取此用户的信息
     * @param request 请求参数
     * @return
     */
    public User find(Map<String, Integer> request);


    /**
     * 添加用户
     * @param user
     * @return
     */
    public Map<String, Object> add(User user);

    /**
     * 移除用户
     * @param request 请求参数
     * @return
     */
    public Map<String, Object> removeOne(Map<String, Integer> request);

    /**
     * 批量移除用户
     * @param ids 请求参数
     * @return
     */
    public Map<String, Object> removeMore(Integer[] ids);

    /**
     * 禁用用户
     * @param request
     * @return
     */
    public Map<String, Object> pause(Map<String, Integer> request);

    /**
     * 启用用户
     * @param request
     * @return
     */
    public Map<String, Object> resume(Map<String, Integer> request);

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    public Map<String, Object> modify(User user);

    /**
     * 修改用户密码
     * @param user
     * @return
     */
    public Map<String, Object> changepwd(User user);

    /**
     * 删除用户
     * @param request 请求参数
     * @return
     */
    public Map<String, Object> deleteOne(Map<String, Integer> request);

    /**
     * 批量删除用户
     * @param ids 请求参数
     * @return
     */
    public Map<String, Object> deleteMore(Integer[] ids);

}