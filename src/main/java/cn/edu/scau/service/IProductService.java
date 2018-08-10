package cn.edu.scau.service;

import cn.edu.scau.component.Info;
import cn.edu.scau.entity.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Component
public interface IProductService {

    Info<List<Product>> selectByName(Map<String,String> request);

    Info<List<Product>> selectByPrice(Map<String,BigDecimal> request);

    Info<Product> updateByPrimaryKeySelective(Product record);

    Info<Product> insert(Product record);

    Info<Product> deleteByPrimaryKey(Map<String ,Integer> request);

}
