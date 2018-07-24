package cn.edu.scau.dao;

import cn.edu.scau.entity.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer id);

    List<Product> selectByPrice(BigDecimal price);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);
}