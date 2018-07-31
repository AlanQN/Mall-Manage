package cn.edu.scau.dao;

import cn.edu.scau.entity.Admin;

public interface AdminMapper {

    /**
     * 根据用户名和密码查找用户，用于登录
     * @param admin
     * @return
     */
    public Admin findByField(Admin admin);

    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

}