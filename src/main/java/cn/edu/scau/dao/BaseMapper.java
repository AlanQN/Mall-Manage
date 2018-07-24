package cn.edu.scau.dao;

import cn.edu.scau.entity.Base;

import java.util.List;

public interface BaseMapper {

    /**
     * 获取系统的基本信息
     * @return
     */
    List<Base> find();

    /**
     * 根据id更新系统信息
     * @param base
     * @return
     */
    int updateById(Base base);

}