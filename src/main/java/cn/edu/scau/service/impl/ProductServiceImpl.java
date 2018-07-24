package cn.edu.scau.service.impl;


import cn.edu.scau.dao.ProductMapper;
import cn.edu.scau.entity.Product;
import cn.edu.scau.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements IProductService{

    @Autowired
    private ProductMapper productMapper;

    @Override
    public Product selectByPrimaryKey(@RequestBody Map<String, Integer> request) {
//        获取id
        Integer id = request.get("id");
//        根据id查找商品信息
        System.out.println(request.get("id"));
        Product product_id = productMapper.selectByPrimaryKey(id);
        return product_id;
    }

    @Override
    public List<Product> selectByPrice(@RequestBody Map<String,BigDecimal> requset) {
        BigDecimal price = requset.get("price");
        System.out.println(price);
        List<Product> product_price = productMapper.selectByPrice(price);
        System.out.println("123");
        return product_price;
    }
}
