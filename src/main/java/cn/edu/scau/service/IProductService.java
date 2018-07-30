package cn.edu.scau.service;

import cn.edu.scau.component.Info;
import cn.edu.scau.entity.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface IProductService {

    Info<Product> selectByPrimaryKey(Map<String,Integer> request);

    Info<Product> selectByPrice(Map<String,BigDecimal> request);

    Info<Product> updateByPrimaryKeySelective(Product record);

    Info<Product> insert(Product record);

    Info<Product> deleteByPrimaryKey(Map<String ,Integer> request);

}
