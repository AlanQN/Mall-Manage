package cn.edu.scau.service;

import cn.edu.scau.component.Info;
import cn.edu.scau.entity.ProductCat;

import java.util.Map;

public interface IProductCatService {

    Info<ProductCat> selectByPrimaryKey(Map<String,Integer> request);

    Info<ProductCat> deleteByPrimaryKey(Map<String ,Integer> request);

    Info<ProductCat> insert(ProductCat record);

    Info<ProductCat> updateByPrimaryKeySelective(ProductCat record);

}
