package cn.edu.scau.service;

import cn.edu.scau.entity.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface IProductService {

    Product selectByPrimaryKey(Map<String,Integer> request);

    List<Product> selectByPrice(Map<String,BigDecimal> request);

}
