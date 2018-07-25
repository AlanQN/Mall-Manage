package cn.edu.scau.dao;

import cn.edu.scau.component.Page;
import cn.edu.scau.entity.Express;

import java.util.List;

public interface ExpressMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(String name);

    int insertSelective(Express record);

    Express selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Express record);

    int updateByPrimaryKey(Express record);

    Express selectByName(String name);

    List<Express> search(String s);

    List<Express> getAll();

    int getTotal(Page<Express> page);

    List<Express> findRecords(Page<Express> page);

    int deleteMore(Integer[] ids);

}