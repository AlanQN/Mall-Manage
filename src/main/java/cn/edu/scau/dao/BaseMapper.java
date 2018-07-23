package cn.edu.scau.dao;

import cn.edu.scau.entity.Base;

public interface BaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Base record);

    int insertSelective(Base record);

    Base selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Base record);

    int updateByPrimaryKey(Base record);
}