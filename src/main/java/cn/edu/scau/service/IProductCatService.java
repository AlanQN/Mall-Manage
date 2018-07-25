package cn.edu.scau.service;

import cn.edu.scau.entity.ProductCat;

import java.util.Map;

public interface IProductCatService {

    ProductCat selectByPrimaryKey(Map<String,Integer> request);

    Map<String ,String>deleteByPrimaryKey(Map<String ,Integer> request);

    Map<String ,String>insert(ProductCat record);

    Map<String ,String> updateByPrimaryKeySelective(ProductCat record);

}
