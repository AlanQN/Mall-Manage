package cn.edu.scau.dao;

import cn.edu.scau.entity.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public interface ProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    // int insertSelective(Product record);

    Product selectByPrimaryKey(Integer id);

    List<Product> selectByPrice(BigDecimal price);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);
}