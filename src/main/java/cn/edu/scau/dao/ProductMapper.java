package cn.edu.scau.dao;

import cn.edu.scau.entity.Product;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public interface ProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    // int insertSelective(Product record);

    /**
     *
     * @param name
     * @return
     */
    List<Product> selectByName(@Param("name") String name);

    List<Product> selectByPrice(BigDecimal price);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    int getTotalProductNum();
}