package cn.edu.scau.dao;

import cn.edu.scau.entity.ProductCat;
import org.springframework.stereotype.Component;


@Component
public interface ProductCatMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductCat record);

    int insertSelective(ProductCat record);

    ProductCat selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductCat record);

    int updateByPrimaryKey(ProductCat record);
}